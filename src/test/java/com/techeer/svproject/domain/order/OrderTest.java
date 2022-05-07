package com.techeer.svproject.domain.order;

import com.techeer.svproject.domain.address.service.AddressService;
import com.techeer.svproject.domain.order.controller.OrderController;
import com.techeer.svproject.domain.order.dto.OrderCreateDto;
import com.techeer.svproject.domain.user.UserRepository;
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

import java.time.LocalDateTime;
import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@SpringBootTest
@AutoConfigureMockMvc
public class OrderTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(OrderTest.class);

    @Autowired
    private OrderController orderController;
    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository userRepository;



    private final String getPath = API_PREFIX + "/orders";
    //.content("{\"id\":\"85f032f0-059e-4de0-a012-099a2acd529b\n\", \"userId\":\"85f032f0-059e-4de0-a012-099a2acd529b\n\", \"orderDate\":\"+1999-12-31T23:59:59.99\"}"

    @Test
    @Transactional
    public void orderPostApi() throws Exception{

        User user = User.builder().firstName("kim").password("1234").lastName("junhyeong").email("ggprgrkjh").phoneNumber(010-0104-4549).build();
        user=userRepository.saveAndFlush(user);

        OrderCreateDto orderCreateDto = OrderCreateDto.builder().id(UUID.randomUUID())
                        .userId(user.getId())
                        .orderDate(LocalDateTime.now())
                                .build();

        mock.perform(post("/api/v1/orders")
                        .content(JsonMapper.asJsonString(orderCreateDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }




}
