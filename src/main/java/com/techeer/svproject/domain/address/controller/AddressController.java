package com.techeer.svproject.domain.address.controller;
import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.address.dto.request.AddressDeleteDto;
import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import com.techeer.svproject.domain.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // 조회
    @GetMapping(API_PREFIX +"/address-list?userEmail={user-email}")
    public void getAddress(@PathVariable(value="user-email")String email) {
        addressService.getAddress(email);
    }

    // 수정
    @PutMapping(API_PREFIX +"/address-list/{address-id}")
    public ResponseEntity updateAddress(@PathVariable(value = "address-id") UUID addressId,
                                        @RequestBody AddressUpdateDto adUpdateDto) {
        Address address = addressService.updateAddress(addressId, adUpdateDto);
        return ResponseEntity
                .ok()
                .body(adUpdateDto.fromEntity(address));
    }

    // 삭제
    @DeleteMapping(API_PREFIX +"/address-list/{address-id}")
    public void deleteAddress(@PathVariable(value = "address-id") UUID addressId) {
        addressService.deleteAddress(addressId);
    }
}
