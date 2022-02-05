package com.techeer.svproject.domain.address;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.techeer.svproject.global.utils.Constants.API_PREFIX;

@RestController
public class AddressController {
    private AddressService addressService;

    @PostMapping(API_PREFIX +"/createAddress")
    public void createaddress(Address new_address) {}

    @GetMapping(API_PREFIX +"/readAddress")
    public List <Address> readaddress(int user_id) {
        List<Address> ad = addressService.searchAddress(user_id);
        return ad;
    }

    @GetMapping(API_PREFIX +"/updateAddress")
    public void updateaddress() {}

    @DeleteMapping(API_PREFIX +"/deleteAddress")
    public void deleteaddress() {}
}
