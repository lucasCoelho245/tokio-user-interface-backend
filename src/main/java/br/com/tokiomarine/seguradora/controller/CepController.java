package br.com.tokiomarine.seguradora.controller;

import br.com.tokiomarine.seguradora.dto.AddressDTO;
import br.com.tokiomarine.seguradora.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<AddressDTO> getAddressByCep(@PathVariable String cep) {
        AddressDTO dto = cepService.getAddressDTOByCep(cep);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
