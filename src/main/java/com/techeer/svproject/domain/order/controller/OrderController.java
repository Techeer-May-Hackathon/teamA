package com.techeer.svproject.domain.order.controller;

import com.techeer.svproject.domain.order.dto.OrderDto;
import com.techeer.svproject.domain.order.entity.Order;
import com.techeer.svproject.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @ResponseBody
    @PostMapping(API_PREFIX + "/orders")
    public OrderDto save(@RequestBody OrderDto orderDto) {
        Order order = orderService.save(orderDto);
        return OrderDto.fromEntity(order);
    }
}
