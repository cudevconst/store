package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.request.AddressRequest;
import com.example.nvcshop.dto.response.AddressResponse;
import com.example.nvcshop.entity.Address;
import com.example.nvcshop.entity.User;
import com.example.nvcshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressMapper {

    @Autowired
    private UserRepository userRepository;
    public static AddressResponse toResponse(Address address){
        AddressResponse addressResponse = AddressResponse.builder()
                .id(address.getId())
                .phoneNumber(address.getPhoneNumber())
                .city(address.getCity())
                .district(address.getDistrict())
                .commune(address.getCommune())
                .details(address.getDetails())
                .createAt(address.getCreateAt())
                .lastModify(address.getLastModify())
                .build();
        return addressResponse;
    }

    public static Address toEntity(AddressRequest addressRequest){

        Address address = Address.builder()
                .phoneNumber(addressRequest.getPhoneNumber())
                .city(addressRequest.getCity())
                .district(addressRequest.getDistrict())
                .commune(addressRequest.getCommune())
                .details(addressRequest.getDetails())
                .build();
        return address;

    }
}
