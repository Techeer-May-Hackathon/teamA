package com.techeer.svproject.domain.user;

import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.order.entity.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer phone_number;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address address;


    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @Builder
    public User(String last_name, String first_name, String email, String password, Integer phone_number, Address address) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.email = email;
        this.password = password;
        this.phone_number = phone_number;
        this.address = address;

    }

}
