package com.techeer.svproject.domain.address;

import com.techeer.svproject.domain.address.service.AddressService;
import com.techeer.svproject.domain.user.dto.UserPostDto;
import com.techeer.svproject.domain.address.dto.request.AddressCreateDto;
import com.techeer.svproject.domain.address.dto.request.AddressReadDto;
import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.dto.UserResponseIdDto;
import com.techeer.svproject.domain.user.dto.UserSaveDto;
import com.techeer.svproject.domain.user.service.UserService;
import com.techeer.svproject.global.utils.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
    @Autowired
    private AddressService addressService;

    private final String getPath = API_PREFIX + "/address-list";

    private String getUpdatePath(UUID addressId) {
        return String.format(API_PREFIX + "/address-list/%s", addressId.toString());
    }

    private final String userPostPath = API_PREFIX + "/users";

    @Test
    @Transactional
    public void testUserPost() throws Exception {
        userPost();
    }

    @Test
    @Transactional
    public void testGetAddress() throws Exception {
        getAddress();
    }

    public UserPostDto userPost() throws Exception {
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

        // find UUID
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

        return UserPostDto.builder().userSaveDto(userSaveDto).uuid(userId).build();
    }

    public UserPostDto getAddress() throws Exception {
        // user POST
        UserPostDto userPostDto = userPost();
        UserSaveDto userSaveDto = userPostDto.getUserSaveDto();

        // find UUID
        UUID userId = userPostDto.getUuid();

        // Address GET
        MvcResult mvcResult = mockMvc.perform(get(getPath)
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

        return userPostDto;
    }

    @Test
    @Transactional
    public void testUpdateAddress() throws Exception {
        // user POST
        UserPostDto userPostDto = getAddress();
        UserSaveDto userSaveDto = userPostDto.getUserSaveDto();

        // find UUID
        UUID userId = userPostDto.getUuid();

        User user = userService.findById(userId);

        Address address = user.getAddress();

        // address PUT

        AddressUpdateDto addressUpdateDto = AddressUpdateDto.builder()
                .state("서울특별시")
                .city("강남구")
                .zipcode(15011)
                .street("선릉").build();

        MvcResult mvcResult = mockMvc.perform(put(getUpdatePath(address.getAddressId()))
                        .content(JsonMapper.asJsonString(addressUpdateDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();


        // check mutated address
        addressUpdateDto = JsonMapper.fromMvcResult(mvcResult, AddressUpdateDto.class);

        assertThat(addressUpdateDto.getState()).isEqualTo("서울특별시");
        assertThat(addressUpdateDto.getCity()).isEqualTo("강남구");
        assertThat(addressUpdateDto.getZipcode()).isEqualTo(15011);
        assertThat(addressUpdateDto.getStreet()).isEqualTo("선릉");

        // check mutated entity
        address = addressService.getAddress(userSaveDto.getEmail());

        assertThat(address.getState()).isEqualTo("서울특별시");
        assertThat(address.getCity()).isEqualTo("강남구");
        assertThat(address.getZipcode()).isEqualTo(15011);
        assertThat(address.getStreet()).isEqualTo("선릉");
    }

}