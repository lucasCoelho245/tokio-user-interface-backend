package br.com.tokiomarine.seguradora.service;

import br.com.tokiomarine.seguradora.dto.AddressDTO;
import br.com.tokiomarine.seguradora.model.Address;
import br.com.tokiomarine.seguradora.model.Client;
import br.com.tokiomarine.seguradora.repository.AddressRepository;
import br.com.tokiomarine.seguradora.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;
    private final RestTemplate restTemplate;

    public List<AddressDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public AddressDTO getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));
        return AddressDTO.fromEntity(address);
    }

    public List<AddressDTO> getAddressesByClientId(Long clientId) {
        return addressRepository.findByClientId(clientId).stream()
                .map(AddressDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public AddressDTO createAddress(AddressDTO addressDTO) {
        Client client = clientRepository.findById(addressDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + addressDTO.getClientId()));

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setDistrict(addressDTO.getDistrict());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipcode(addressDTO.getZipcode());
        address.setClient(client);

        Address savedAddress = addressRepository.save(address);
        return AddressDTO.fromEntity(savedAddress);
    }

    @Transactional
    public AddressDTO updateAddress(Long id, AddressDTO newAddressDTO) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));

        address.setStreet(newAddressDTO.getStreet());
        address.setDistrict(newAddressDTO.getDistrict());
        address.setCity(newAddressDTO.getCity());
        address.setState(newAddressDTO.getState());
        address.setZipcode(newAddressDTO.getZipcode());

        Address updatedAddress = addressRepository.save(address);
        return AddressDTO.fromEntity(updatedAddress);
    }

    @Transactional
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com ID: " + id));
        addressRepository.delete(address);
    }

    public AddressDTO getAddressByCep(String cep) {
        String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";
        AddressDTO address = restTemplate.getForObject(apiUrl, AddressDTO.class);
        if (address == null) {
            throw new RuntimeException("Endereço não encontrado para o CEP: " + cep);
        }
        return address;
    }
}
