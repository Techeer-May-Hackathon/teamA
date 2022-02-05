package com.techeer.svproject.domain.address.dto.request;
import com.techeer.svproject.domain.address.Address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class AddressUpdateDto {
    private UUID addressId;
    private String state;
    private String city;
    private String street;
    private int zipcode;

    @Builder
    public AddressUpdateDto(String state, String city, String street, int zipcode) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    // 수정 DTO - Address
    public Address toEntity(AddressUpdateDto dto){
        return Address.builder()
                .state(dto.getState())
                .city(dto.getCity())
                .street(dto.getStreet())
                .zipcode(dto.getZipcode())
                .build();
    }

}
