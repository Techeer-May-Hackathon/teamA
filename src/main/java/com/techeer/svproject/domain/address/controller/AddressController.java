package com.techeer.svproject.domain.address.controller;
import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import com.techeer.svproject.domain.address.service.AddressService;
import org.springframework.web.bind.annotation.*;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RestController
public class AddressController {
    private AddressService addressService;
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
    public void updateaddress(@RequestBody AddressUpdateDto adto) {
        addressService.updateAddress(adto);
    }

//    @DeleteMapping(API_PREFIX +"/address-list/{address-id}")
//    public void deleteaddress(@RequestBody int address_id) {
//        addressService.deleteAddress(address_id);
//    }
}
