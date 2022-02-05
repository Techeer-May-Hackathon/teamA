package com.techeer.svproject.domain.products.service;

import com.sun.istack.NotNull;
import com.techeer.svproject.domain.products.dto.ProductSaveDto;
import com.techeer.svproject.domain.products.entity.Product;
import com.techeer.svproject.domain.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
//    private final EntityManager em;
    private final ProductRepository productRepository;

    @Transactional
    public UUID save(ProductSaveDto productSaveDto){
        return productRepository.save(productSaveDto.toEntity()).getId();
    }

    /*
    public List<Product> findItems(){
        return em.createQuery("select i from Item i", Product.class).getResultList();
    }

    public Product findOne(Long productId){
        return findOne(productId);
        return em.find(Product.class, productId);
    }
    */
}
