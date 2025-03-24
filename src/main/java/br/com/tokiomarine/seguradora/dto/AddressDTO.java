package br.com.tokiomarine.seguradora.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AddressDTO {
    private String zipcode;
    private String street;
    private String complement;
    private String district;
    private String city;
    private String state;
}
