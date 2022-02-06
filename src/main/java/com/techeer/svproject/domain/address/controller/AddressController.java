package com.techeer.svproject.domain.address.controller;
import com.techeer.svproject.domain.address.dto.request.AddressDeleteDto;
import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import com.techeer.svproject.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RequiredArgsConstructor
@RestController
public class AddressController {
    private final AddressService addressService;
//    @PostMapping(API_PREFIX +"/address-list/{address-id}")
//    public Address createaddress(@RequestBody addressDto new_address) {
//    }
//
//    @GetMapping(API_PREFIX +"/address-list/{address-id}")
//    public List <Address> readaddress(@RequestBody int user_id) {
//        List<Address> ad = addressService.searchAddress(user_id);
//        return ad;
//    }

    // 수정
    @PutMapping(API_PREFIX +"/address-list/{address-id}")
    public UUID updateAddress(@PathVariable(value = "address-id") UUID addressId,
                              @RequestBody AddressUpdateDto adUpdateDto) {
        System.out.println(addressId);
        System.out.println(adUpdateDto);
        return addressService.updateAddress(addressId, adUpdateDto);
    }

    // 삭제
    @DeleteMapping(API_PREFIX +"/address-list/{address-id}")
    public void deleteAddress(@PathVariable(value = "address-id") UUID addressId,
                              @RequestBody AddressDeleteDto addo) {
        addressService.deleteAddress(addressId, addo);
    }
}
