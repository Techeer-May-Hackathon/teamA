package com.techeer.svproject.domain.address.service;
import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.address.dto.request.AddressCreateDto;
import com.techeer.svproject.domain.address.dto.request.AddressDeleteDto;
import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import org.springframework.stereotype.Service;
import com.techeer.svproject.domain.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

import java.util.*;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    // User Address 수정
    public UUID updateAddress(UUID addressId, AddressUpdateDto adu){
        Address address = addressRepository.getById(addressId);
        address.update(adu.getState(),adu.getCity(),adu.getStreet(),adu.getZipcode());
        //addressRepository.updateAddress(astate,astreet,acity,azc,aid);
        addressRepository.save(address);
        System.out.println("Updated");
        return addressId;
    }

    // User Address 생성
    public UUID createAddress(AddressCreateDto addressCreateDto) {
        return addressRepository.save(addressCreateDto.toEntity()).getAddressId();
    }

    // User Address 삭제
    public void deleteAddress(UUID addressId, AddressDeleteDto addressDeleteDto){
        System.out.println("DELETED");
    }
}