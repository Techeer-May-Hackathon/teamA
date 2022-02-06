package com.techeer.svproject.domain.order.dto;

import com.techeer.svproject.domain.order.entity.Order;
import com.techeer.svproject.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private UUID id;
    private UUID userId;
    private LocalDateTime orderDate;

    public Order toEntity() {
        return Order.builder()
                .orderDate(orderDate)
                .build();
    }

    public static OrderDto fromEntity(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .orderDate(order.getOrderDate())
                .build();
    }
}
