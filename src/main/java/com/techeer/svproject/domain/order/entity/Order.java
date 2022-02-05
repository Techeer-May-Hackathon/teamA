package com.techeer.svproject.domain.order.entity;

import com.techeer.svproject.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 16)
    private UUID id;

    @Setter
    @ManyToOne
    private User user;

//    @Setter
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    private Set<Product> products = new HashSet<>();

    /** 주문 날짜 */
    @Column(name = "`orderDate`")
    @CreationTimestamp
    private Date orderDate;

    @Builder
    public Order(
            User user,
            Date orderDate) {
        this.user = user;
        this.orderDate = orderDate;
    }

    public void update(
            User user,
            Date orderDate
           //, Set<Product> products
            ) {
        this.user = user;
        this.orderDate = orderDate;
    }
}
