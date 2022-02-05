package com.techeer.svproject.domain.products.dto;

import com.techeer.svproject.domain.products.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class ProductSaveDto {
    private UUID orderId;
    private int price;
    private String productName;

    @Builder
    public ProductSaveDto(UUID orderId, int price, String productName) {
        this.orderId = orderId;
        this.price = price;
        this.productName = productName;
    }

    public Product toEntity() {
        return Product.builder()
                .orderId(orderId)
                .price(price)
                .productName(productName)
                .build();
    }
}
