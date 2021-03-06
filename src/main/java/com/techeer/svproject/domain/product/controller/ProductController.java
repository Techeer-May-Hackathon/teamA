package com.techeer.svproject.domain.product.controller;

import com.techeer.svproject.domain.order.dto.OrderResponseDto;
import com.techeer.svproject.domain.product.dto.ProductResponseDto;
import com.techeer.svproject.domain.product.dto.ProductSaveDto;
import com.techeer.svproject.domain.product.dto.ProductUpdateDto;
import com.techeer.svproject.domain.product.entity.Product;
import com.techeer.svproject.domain.product.service.ProductService;
import com.techeer.svproject.global.utils.dto.ErrorResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_PREFIX + "/products")
public class ProductController {
    private final ProductService productService;

    /**
     * 상품 저장
     **/
    @ResponseBody
    @PostMapping
    public ResponseEntity<ProductSaveDto> save(@RequestBody ProductSaveDto productSaveDto) {
        Product product = productService.save(productSaveDto);
        return new ResponseEntity<>(ProductSaveDto.fromEntity(product), HttpStatus.CREATED);
    }


    //responseEntity-> 201port (위에 참조해서 밑에 바꿔주기 )


    /**
     * 상품 조회
     **/
    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity getDetail(@PathVariable UUID id) {

        Optional<Product> entity = productService.findById(id);
        return ResponseEntity
                .ok()
                .body(ProductSaveDto.fromEntity(entity.get()));
    }

    @ResponseBody
    @PutMapping(API_PREFIX + "/products/{id}")
    public ResponseEntity<OrderResponseDto> updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateDto requestDto) {
        Product entity = productService.update(id, requestDto);
        try {
            return new ResponseEntity(ProductUpdateDto.fromEntity(entity), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(ErrorResponseDto.fromEntity("FORBIDDEN", "상품 수정에 오류가 발생하였습니다."), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 주문 목록 조회
     **/
    @ResponseBody
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getByOrderId(@RequestParam UUID orderId) {

        List<ProductResponseDto> entity = productService.findAllByOrderId(orderId)
                .stream()
                .collect(Collectors.toList());
        try {
            return ResponseEntity
                    .ok()
                    .body(productService.findAllByOrderId(orderId));
        } catch (Exception e) {
            return new ResponseEntity(ErrorResponseDto.fromEntity("FORBIDDEN", "상품 목록 조회에 오류가 발생하였습니다."), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 상품 삭제
     **/
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable UUID id) {

        productService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
