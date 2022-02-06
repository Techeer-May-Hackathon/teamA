package com.techeer.svproject.domain.user.controller;

import com.techeer.svproject.domain.address.service.AddressService;
import com.techeer.svproject.domain.user.dto.UserResponseDto;
import com.techeer.svproject.domain.user.dto.UserResponseIdDto;
import com.techeer.svproject.domain.user.service.UserService;
import com.techeer.svproject.domain.user.dto.UserSaveDto;
import com.techeer.svproject.global.utils.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;
    private final AddressService addressService;

    @PostMapping(API_PREFIX + "/users")
    public ResponseEntity save(@RequestBody UserSaveDto requestDTO) {

        // 스프링은 너가 객체를 만들면 그 객체를 json 형태로 만들어줌.
        // UUID -> String
        // {
        //   "id": "43223rjhekshfdskfj"
        // }

        // 1. Map으로 객체를 만들어준다
        // 2. DTO를 만들어준다

        try {

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(UserResponseIdDto.fromEntity(this.userService.save(requestDTO)));
//                            Map.of("id", this.userService.save(requestDTO)));
        }
        catch(Exception e){
            if(userService.checkEmailDuplicate(requestDTO.getEmail())) {
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(ErrorResponseDto.fromEntity("FORBIDDEN", "이메일이 중복되었습니다."));
            }
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("오류");
        }

    }

    @GetMapping(API_PREFIX + "/users")
    public List<UserResponseDto> index(Model model){
        model.addAttribute("user", userService.findAll());
        return userService.findAll();
    }

    @GetMapping(API_PREFIX + "/users/{email}")
    public UserResponseDto find (@PathVariable String email){
        return userService.findByEmail(email);
    }

}
