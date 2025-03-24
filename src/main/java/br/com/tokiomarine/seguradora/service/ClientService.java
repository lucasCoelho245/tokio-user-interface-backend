package br.com.tokiomarine.seguradora.service;

import br.com.tokiomarine.seguradora.exception.ResourceNotFoundException;
import br.com.tokiomarine.seguradora.model.Client;
import br.com.tokiomarine.seguradora.repository.ClientRepository;
import br.com.tokiomarine.seguradora.validation.ClientValidator;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientValidator clientValidator;

    public ClientService(ClientRepository clientRepository, ClientValidator clientValidator) {
        this.clientRepository = clientRepository;
        this.clientValidator = clientValidator;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public Client saveClient(Client client) {
        clientValidator.validateClient(client);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
