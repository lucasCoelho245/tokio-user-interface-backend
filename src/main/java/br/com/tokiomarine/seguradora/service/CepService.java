package br.com.tokiomarine.seguradora.service;

import br.com.tokiomarine.seguradora.dto.AddressDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CepService {

    private final RestTemplate restTemplate;

    public CepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AddressDTO getAddressDTOByCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        Map<String, String> response = restTemplate.getForObject(url, Map.class);

        if (response == null || response.containsKey("erro")) {
            return null;
        }

        return AddressDTO.fromViaCepResponse(response);
    }

}
