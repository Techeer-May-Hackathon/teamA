package com.techeer.svproject.domain.order.controller;

import com.techeer.svproject.domain.order.dto.OrderCreateDto;
import com.techeer.svproject.domain.order.dto.OrderMapper;
import com.techeer.svproject.domain.order.dto.OrderResponseDto;
import com.techeer.svproject.domain.order.entity.Order;
import com.techeer.svproject.domain.order.service.OrderService;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_PREFIX+"/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final OrderMapper orderMapper;

    @ResponseBody
    @PostMapping
    public ResponseEntity<OrderResponseDto> save(@RequestBody OrderCreateDto requestDto) {
        User user = userService.findById(requestDto.getUserId());
        Order entity = orderService.save(orderMapper.toEntity(requestDto), user);
        return new ResponseEntity<>(orderMapper.toResponseDto(entity), HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getDetail(@PathVariable UUID id) {

        Order entity = orderService.findById(id);

        return ResponseEntity
                .ok()
                .body(orderMapper.toResponseDto(entity));
    }
}
