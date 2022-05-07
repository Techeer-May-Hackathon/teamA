package com.techeer.svproject.domain.user.dto;

import com.techeer.svproject.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestUpdateDto {
    private String firstName;
    private String lastName;
    private String password;
    private int phoneNumber;
    private Address address;

}
