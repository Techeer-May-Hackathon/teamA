package com.techeer.svproject.domain.address;

import com.techeer.svproject.domain.address.controller.AddressController;
import com.techeer.svproject.domain.address.dto.request.AddressCreateDto;
import com.techeer.svproject.domain.address.dto.request.AddressReadDto;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.UserRepository;
import com.techeer.svproject.domain.user.dto.UserResponseIdDto;
import com.techeer.svproject.domain.user.dto.UserSaveDto;
import com.techeer.svproject.domain.user.service.UserService;
import com.techeer.svproject.global.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AddressTest.class);

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    private final String getPath = API_PREFIX + "/address-list";

    private String getUpdatePath(UUID addressId) {
        return String.format(API_PREFIX + "/address-list/%s", addressId.toString());
    }

    private final String userPostPath = API_PREFIX + "/users";

    @Test
    @Transactional
    public void testGetAddress() throws Exception {
        UserSaveDto userSaveDto = UserSaveDto.builder()
                .firstName("jiho")
                .lastName("lee")
                .email("optional.int@kakao.com")
                .password("12341weqe")
                .phoneNumber(123456789)
                .address(AddressCreateDto.builder()
                                .state("경기도")
                                .city("시흥시")
                                .zipcode(15010)
                                .street("배곧").build())
                .build();


        // user POST
        MvcResult mvcResult = mockMvc.perform(post(userPostPath)
                .content(JsonMapper.asJsonString(userSaveDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        UserResponseIdDto userResponseIdDto = JsonMapper.fromMvcResult(mvcResult, UserResponseIdDto.class);

        UUID userId = userResponseIdDto.getId();

        User registered = userService.findById(userId);

        assertThat(registered).isNotNull();

        log.info(userSaveDto.toString());
        log.info(registered.toString());

        // Check object fields
        assertThat(registered.getFirstName()).isEqualTo(userSaveDto.getFirstName());
        assertThat(registered.getLastName()).isEqualTo(userSaveDto.getLastName());
        assertThat(registered.getEmail()).isEqualTo(userSaveDto.getEmail());
        assertThat(registered.getPhoneNumber()).isEqualTo(userSaveDto.getPhoneNumber());


        // Address GET
        mvcResult = mockMvc.perform(get(getPath)
                .accept(MediaType.APPLICATION_JSON)
                        .queryParam("userEmail", userSaveDto.getEmail()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        AddressReadDto addressReadDto = JsonMapper.fromMvcResult(mvcResult, AddressReadDto.class);
        AddressCreateDto address = userSaveDto.getAddress();

        // Check address fields
        assertThat(addressReadDto.getState()).isEqualTo(address.getState());
        assertThat(addressReadDto.getCity()).isEqualTo(address.getCity());
        assertThat(addressReadDto.getStreet()).isEqualTo(address.getStreet());
        assertThat(addressReadDto.getZipcode()).isEqualTo(address.getZipcode().intValue());

    }

    @Test
    public void testUpdateAddress() throws Exception {
        // user POST

        // find UUID

        // address PUT
    }
}
