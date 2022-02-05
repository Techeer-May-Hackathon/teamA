package com.techeer.svproject.domain.products.repository;

import com.techeer.svproject.domain.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductInfo extends JpaRepository<Product, UUID> {
}

