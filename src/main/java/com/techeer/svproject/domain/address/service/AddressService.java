package com.techeer.svproject.domain.address.service;

import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import org.springframework.stereotype.Service;
import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressUpdateDto addressDtoUpdate;

    // User Address 수정
    public void updateAddress(AddressUpdateDto adu){
        UUID aid = adu.getAddressId();
        String astate = adu.getState();
        String astreet = adu.getStreet();
        String acity = adu.getCity();
        int azc = adu.getZipcode();
        addressRepository.updateAddress(astate,astreet,acity,azc,aid);
    }

    // User Address 생성
    public UUID createAddress(AddressCreateDto addressCreateDto) {
        return addressRepository.save(addressCreateDto.toEntity()).getAddress_id();
    }
}