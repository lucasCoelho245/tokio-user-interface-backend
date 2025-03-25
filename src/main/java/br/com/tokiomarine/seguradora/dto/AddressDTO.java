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
}
