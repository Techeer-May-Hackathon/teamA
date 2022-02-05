package com.techeer.svproject.domain.user.dto;

import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.address.dto.AddressCreateDto;
import com.techeer.svproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private UUID Id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private Address address;

    public UserResponseDto(User entity){
        this.Id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.address = entity.getAddress();
    }
}
