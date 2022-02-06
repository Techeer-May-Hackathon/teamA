package com.techeer.svproject.domain.products.controller;
import com.techeer.svproject.domain.products.dto.ProductSaveDto;
import com.techeer.svproject.domain.products.entity.Product;
import com.techeer.svproject.domain.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @ResponseBody
    @PostMapping(API_PREFIX + "/products")
    public ProductSaveDto save(@RequestBody ProductSaveDto productSaveDto) {
        Product product = productService.save(productSaveDto);
        return ProductSaveDto.fromEntity(product);
    }
}
