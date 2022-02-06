package com.techeer.svproject.domain.order.controller;

import com.techeer.svproject.domain.order.dto.OrderDto;
import com.techeer.svproject.domain.order.entity.Order;
import com.techeer.svproject.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @GetMapping(API_PREFIX+"/orders/{id}")
    public ResponseEntity getDetail(@PathVariable UUID id) {

        Order entity = orderService.findById(id);

        return ResponseEntity
                .ok()
                .body(OrderDto.fromEntity(entity));
    }
}
