package com.techeer.svproject.domain.user.dto;

import com.techeer.svproject.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveDto {
    private String last_name;
    private String first_name;
    private String email;
    private String password;
    private Integer phone_number;
    @Builder
    public UserSaveDto(String last_name, String first_name, String email, String password, Integer phone_number){
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
    }

    public User toEntity(){
        return User.builder()
                .first_name(first_name)
                .last_name(last_name)
                .email(email)
                .password(password)
                .phone_number(phone_number)
                .build();
    }
}
