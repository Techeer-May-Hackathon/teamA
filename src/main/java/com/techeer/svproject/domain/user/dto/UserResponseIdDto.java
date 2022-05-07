package com.techeer.svproject.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseIdDto {
    private UUID id;

    public static UserResponseIdDto fromEntity(UUID id) {
        return UserResponseIdDto.builder()
                .id(id)
                .build();
    }
}
