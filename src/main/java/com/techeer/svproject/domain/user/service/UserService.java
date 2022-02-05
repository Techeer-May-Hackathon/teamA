package com.techeer.svproject.domain.user.service;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.UserRepository;
import com.techeer.svproject.domain.user.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UUID save(UserSaveDto userSaveDto) {
        String encodedPassword = Hashing.sha256().hashString(userSaveDto.getPassword(), StandardCharsets.UTF_8).toString();

        User user = User.builder()
                .firstName(userSaveDto.getFirstName())
                .lastName(userSaveDto.getLastName())
                .email(userSaveDto.getEmail())
                .phoneNumber(userSaveDto.getPhoneNumber())
                .password(encodedPassword)
                .build();
        user.setAddress(userSaveDto.getAddress().toEntity());
        return userRepository.save(user).getId();
    }
}
