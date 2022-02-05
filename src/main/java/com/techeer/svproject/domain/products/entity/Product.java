package com.techeer.svproject.domain.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID product_id;

    @Column(nullable = false)
    private UUID order_id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String product_name;
}
