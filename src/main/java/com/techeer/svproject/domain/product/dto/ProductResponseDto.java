package com.techeer.svproject.domain.product.dto;

import com.techeer.svproject.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {
    private UUID Id;
    private int price;
    private String productName;

    public ProductResponseDto(Product entity){
        this.Id = entity.getId();
        this.price = entity.getPrice();
        this.productName = entity.getProductName();
    }
}
