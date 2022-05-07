/*
상품 저장
 */


package com.techeer.svproject.domain.product;

import com.techeer.svproject.domain.order.dto.OrderResponseDto;
import com.techeer.svproject.domain.product.dto.ProductSaveDto;
import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.UserRepository;
import com.techeer.svproject.domain.user.dto.UserResponseDto;
import com.techeer.svproject.domain.user.dto.UserResponseIdDto;
import com.techeer.svproject.global.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(ProductController.class)
public class SaveProductTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SaveProductTest.class);

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private UserRepository userRepository;


    private final String testUserPath = API_PREFIX + "/users";

    private final String testOrderPath = API_PREFIX + "/orders";

    private final String testProductPath = API_PREFIX + "/products";


    @Test
    @Transactional
    public void testProductExample() throws Exception {                 //예시로 주는 값 (product 공통)

        //User 정보 입력
        User user = User.builder()
                .lastName("Dayon")
                .firstName("Hong")
                .email("dayon@gmail.com")
                .phoneNumber(12345678)
                .password("12345678")
                .address(Address.builder()
                        .state("경기도")
                        .city("부천시")
                        .zipcode(14596)
                        .street("상동").build())
                .build();

        user = userRepository.saveAndFlush(user);

        UserResponseDto userResponseDto = UserResponseDto.fromEntity(user);

        // User API 호출
        MvcResult mvcResult = mockMvc.perform(post(testUserPath)
                        .content(JsonMapper.asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        UserResponseIdDto userResponseIdDto = JsonMapper.fromMvcResult(mvcResult, UserResponseIdDto.class);
        //find UUID
        UUID userId = userResponseIdDto.getId();




        //Order 정보 입력
        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .id(UUID.randomUUID())
                .userId(user.getId())
                .build();


        // OrderController에서 Order POST API 호출                   
        mvcResult = mockMvc.perform(post(testOrderPath)
                        .content(JsonMapper.asJsonString(orderResponseDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        orderResponseDto = JsonMapper.fromMvcResult(mvcResult, OrderResponseDto.class);

        //find UUID
        UUID orderId = orderResponseDto.getId();
        // log.info(orderResponseDto.toString());     //상태확인



        //Product 정보 입력
        ProductSaveDto productSaveDto = ProductSaveDto.builder()
                .id(UUID.randomUUID())
                .orderId(orderId)
                .price(5500)
                .productName("아이스크림")
                .build();


        //ProductController 에서 Product Post API 호출
        mvcResult = mockMvc.perform(post(testProductPath)
                        .content(JsonMapper.asJsonString(productSaveDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        productSaveDto = JsonMapper.fromMvcResult(mvcResult, ProductSaveDto.class);
        UUID id = productSaveDto.getId();

        // Check object fields - 값 비교
        //assertThat(productSaveDto.getId()).isEqualTo(id);
        assertThat(productSaveDto.getOrderId()).isEqualTo(orderId);
        assertThat(productSaveDto.getPrice()).isEqualTo(5500);
        assertThat(productSaveDto.getProductName()).isEqualTo("아이스크림");


    }
}
