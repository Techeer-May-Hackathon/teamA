package com.techeer.svproject.domain.order.service;

import com.techeer.svproject.domain.order.dto.OrderDto;
import com.techeer.svproject.domain.order.entity.Order;
import com.techeer.svproject.domain.order.repository.OrderRepository;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order save(OrderDto orderDto) {
        Order order = orderDto.toEntity();
        User user = userRepository.findById(orderDto.getUserId()).get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    @Transactional
    public Order findById(UUID id) {
       return orderRepository.findById(id).get();
    }
}
