package com.techeer.svproject.domain.user.dto;

import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.address.dto.AddressCreateDto;
import com.techeer.svproject.domain.order.dto.OrderDto;
import com.techeer.svproject.domain.order.entity.Order;
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
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private Address address;

    public UserResponseDto(User entity){
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();
        this.phoneNumber = entity.getPhoneNumber();
        this.address = entity.getAddress();
    }

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .firstName(user.getFirstName())
                .Id(user.getId())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
    public static UserResponseDto fromEntity(UUID id) {
        return UserResponseDto.builder()
                .Id(id)
                .build();
    }
}
