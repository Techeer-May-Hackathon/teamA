package com.techeer.svproject.domain.user.controller;

import com.techeer.svproject.domain.address.service.AddressService;
import com.techeer.svproject.domain.user.dto.UserResponseDto;
import com.techeer.svproject.domain.user.service.UserService;
import com.techeer.svproject.domain.user.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final AddressService addressService;

    @PostMapping(API_PREFIX + "/users")
    public UUID save(
            @RequestBody
            UserSaveDto requestDTO
    ) {
        return userService.save(requestDTO);
    }

    @GetMapping(API_PREFIX + "/users")
    public List<UserResponseDto> index(Model model){
        model.addAttribute("user", userService.findAll());
        return userService.findAll();
    }

    @GetMapping(API_PREFIX + "/users/{email}")
    public List<UserResponseDto> find (@PathVariable String email){
        return userService.findByEmail(email);
    }

}
