package br.com.tokiomarine.seguradora.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ClientDTO {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private List<br.com.tokiomarine.seguradora.dto.AddressDTO> addresses;
}
