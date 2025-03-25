package br.com.tokiomarine.seguradora.controller;

import br.com.tokiomarine.seguradora.model.Address;
import br.com.tokiomarine.seguradora.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CepController {
    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Address> getAddress(@PathVariable String cep) {
        Address address = cepService.getAddressByCep(cep);
        return address != null ? ResponseEntity.ok(address) : ResponseEntity.notFound().build();
    }
}
