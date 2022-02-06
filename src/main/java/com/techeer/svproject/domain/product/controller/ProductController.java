package com.techeer.svproject.domain.product.controller;
import com.techeer.svproject.domain.product.dto.ProductSaveDto;
import com.techeer.svproject.domain.product.entity.Product;
import com.techeer.svproject.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

    @ResponseBody
    @GetMapping(API_PREFIX+"/products/{id}")
    public ResponseEntity getDetail(@PathVariable UUID id) {

        Optional<Product> entity = productService.findById(id);

        return ResponseEntity
                .ok()
                .body(ProductSaveDto.fromEntity(entity.get()));
    }
}
