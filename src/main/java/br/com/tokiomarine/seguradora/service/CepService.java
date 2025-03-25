package br.com.tokiomarine.seguradora.service;

import br.com.tokiomarine.seguradora.model.Address;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CepService {

    public Address getAddressByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String API_URL = "https://api.brasilaberto.com/v1/zipcode/";
        ResponseEntity<Map> response = restTemplate.getForEntity(API_URL + cep, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            Map<String, Object> result = (Map<String, Object>) response.getBody().get("result");

            Address address = new Address();
            address.setStreet((String) result.get("street"));
            address.setComplement((String) result.get("complement"));
            address.setDistrict((String) result.get("district"));
            address.setCity((String) result.get("city"));
            address.setState((String) result.get("state"));
            address.setZipcode(cep);

            return address;
        }

        return null;
    }
}
