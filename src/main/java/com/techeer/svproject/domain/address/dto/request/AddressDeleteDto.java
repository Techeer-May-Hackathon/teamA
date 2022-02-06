package com.techeer.svproject.domain.address.dto.request;
import com.techeer.svproject.domain.address.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class AddressDeleteDto {
    private String state;
    private String city;
    private String street;
    private int zipcode;

    @Builder
    public AddressDeleteDto(String state, String city, String street, int zipcode) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
