package com.techeer.svproject.domain.address.service;

import com.techeer.svproject.domain.address.dto.AddressCreateDto;
import org.springframework.stereotype.Service;
import com.techeer.svproject.domain.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    // User Address 생성
    public UUID createAddress(AddressCreateDto addressCreateDto) {
        return addressRepository.save(addressCreateDto.toEntity()).getAddress_id();
    }
}