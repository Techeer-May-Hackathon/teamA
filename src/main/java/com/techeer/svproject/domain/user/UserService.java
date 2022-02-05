package com.techeer.svproject.domain.user;

import com.techeer.svproject.domain.user.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UUID save(UserSaveDto userSaveDto) {return userRepository.save(userSaveDto.toEntity()).getId();}
}
