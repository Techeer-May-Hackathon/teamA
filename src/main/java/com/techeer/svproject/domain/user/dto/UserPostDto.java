package com.techeer.svproject.domain.user.dto;

import com.techeer.svproject.domain.user.dto.UserSaveDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPostDto {
    public UserSaveDto userSaveDto;
    public UUID uuid;
}