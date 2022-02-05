package com.techeer.svproject.domain.order.repository;

import com.techeer.svproject.domain.order.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    // Page<Products> findAll(Pageable pageable);
}
