package br.com.tokiomarine.seguradora.validation;

import br.com.tokiomarine.seguradora.exception.EmailAlreadyExistsException;
import br.com.tokiomarine.seguradora.exception.CpfAlreadyExistsException;
import br.com.tokiomarine.seguradora.model.Client;
import br.com.tokiomarine.seguradora.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientValidator {

    private final ClientRepository clientRepository;

    public ClientValidator(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void validateClient(Client client) {
        Optional<Client> existingClientByEmail = clientRepository.findByEmail(client.getEmail());
        if (existingClientByEmail.isPresent()) {
            throw new EmailAlreadyExistsException("O e-mail '" + client.getEmail() + "' já está cadastrado.");
        }

        Optional<Client> existingClientByCpf = clientRepository.findByCpf(client.getCpf());
        if (existingClientByCpf.isPresent()) {
            throw new CpfAlreadyExistsException("O CPF '" + client.getCpf() + "' já está cadastrado.");
        }
    }
}
