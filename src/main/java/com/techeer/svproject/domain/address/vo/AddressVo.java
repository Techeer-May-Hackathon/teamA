package com.techeer.svproject.domain.address.vo;

import lombok.*;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AddressVo {
    private String state;
    private String city;
    private String street;
    private int zipcode;
}
