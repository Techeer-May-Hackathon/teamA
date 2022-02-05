package com.techeer.svproject.domain.address;

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

    // User Address 생성
    public void createAddress(Address address) {
        addressRepository.save(address);
    }

    // User Address 조회
    public List <Address> searchAddress(int user_id){
        List <Address> addressList = addressRepository.searchAddress(user_id);
        return addressList;
    }

    // User Address 수정
    public void updateAddress(Address address){
        UUID address_id = address.getAddress_id();

    }


    // User Address 삭제
    public void deleteAddress(int address_id){

    }

}
