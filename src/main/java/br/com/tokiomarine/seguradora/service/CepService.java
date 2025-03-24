package br.com.tokiomarine.seguradora.service;

import br.com.tokiomarine.seguradora.dto.AddressDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    private static final String API_URL = "https://api.brasilaberto.com/v1/zipcode/";

    public AddressDTO getAddressByCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + cep;
        return restTemplate.getForObject(url, AddressDTO.class);
    }
}
