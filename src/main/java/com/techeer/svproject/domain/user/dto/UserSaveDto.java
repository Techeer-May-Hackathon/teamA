package com.techeer.svproject.domain.user.dto;

import com.techeer.svproject.domain.address.dto.request.AddressCreateDto;
import com.techeer.svproject.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSaveDto {
    private String last_name;
    private String first_name;
    private String email;
    private String password;
    private Integer phone_number;
    private AddressCreateDto address;

    public User toEntity() {
        return User.builder()
                .first_name(first_name)
                .last_name(last_name)
                .email(email)
                .password(password)
                .phone_number(phone_number)
                .build();
    }
}
