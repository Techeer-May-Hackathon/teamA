package com.techeer.svproject.domain.address;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID addressId;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int zipcode;

    @Builder
    public Address(String state, String city, String street, int zipcode) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public void update(String state, String city, String street, int zipcode) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
