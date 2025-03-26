package br.com.tokiomarine.seguradora.dto;

import br.com.tokiomarine.seguradora.model.Address;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String street;
    private String district;
    private String city;
    private String state;
    private String zipcode;
    private Long clientId;

    // Construtor usado quando vem da entidade
    public static AddressDTO fromEntity(Address address) {
        return new AddressDTO(
                address.getId(),
                address.getStreet(),
                address.getDistrict(),
                address.getCity(),
                address.getState(),
                address.getZipcode(),
                address.getClient() != null ? address.getClient().getId() : null
        );
    }

    public static AddressDTO fromViaCepResponse(java.util.Map<String, String> response) {
        AddressDTO dto = new AddressDTO();
        dto.setStreet(response.get("logradouro"));
        dto.setDistrict(response.get("bairro"));
        dto.setCity(response.get("localidade"));
        dto.setState(response.get("uf"));
        dto.setZipcode(response.get("cep"));
        return dto;
    }
}
