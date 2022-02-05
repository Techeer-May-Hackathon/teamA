package com.techeer.svproject.domain.products.entity;

import com.techeer.svproject.domain.order.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 16)
    private UUID id;

    @Setter
    @ManyToOne
    private Order order;

//    @Column(nullable = false)
//    private UUID orderId;

    /**상품 가격**/
    @Column(nullable = false)
    private int price;

    /**상품 이름**/
    @Column(nullable = false)
    private String productName;

    @Builder
    public Product(
            Order order,
            int price,
            String productName) {
        this.order = order;
        this.price = price;
        this.productName = productName;
    }

}
