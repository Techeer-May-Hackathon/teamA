package com.techeer.svproject.domain.address.service;
import com.techeer.svproject.domain.address.Address;
import com.techeer.svproject.domain.address.dto.request.AddressCreateDto;
import com.techeer.svproject.domain.address.dto.request.AddressDeleteDto;
import com.techeer.svproject.domain.address.dto.request.AddressUpdateDto;
import com.techeer.svproject.domain.user.User;
import com.techeer.svproject.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import com.techeer.svproject.domain.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    // User Address 수정
    public Address updateAddress(UUID addressId, AddressUpdateDto adu){
        Address address = addressRepository.getById(addressId);
        address.update(adu.getState(),adu.getCity(),adu.getStreet(),adu.getZipcode());
        return addressRepository.save(address);
    }

    // User Address 생성
    public UUID createAddress(AddressCreateDto addressCreateDto) {
        return addressRepository.save(addressCreateDto.toEntity()).getAddressId();
    }

    // User Address 조회
    public Address getAddress(String email){
        User user = userRepository.findByEmail(email);
        Address address = user.getAddress();
        return address;
    }
}
