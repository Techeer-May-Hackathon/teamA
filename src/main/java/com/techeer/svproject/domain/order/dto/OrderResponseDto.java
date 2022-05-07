package com.techeer.svproject.domain.order.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponseDto {
    private UUID id;
    private UUID userId;
    private LocalDateTime orderDate;
}
