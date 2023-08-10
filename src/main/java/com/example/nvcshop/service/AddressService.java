package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.AddressRequest;
import com.example.nvcshop.dto.response.AddressResponse;
import com.example.nvcshop.entity.Address;
import com.example.nvcshop.entity.User;
import com.example.nvcshop.mapper.AddressMapper;
import com.example.nvcshop.repository.AddressRepository;
import com.example.nvcshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public AddressResponse getAddressById(String addressId){
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();
            AddressResponse addressResponse = AddressMapper.toResponse(address);

            return addressResponse;
        }
        return null;
    }
    public List<AddressResponse> getAllAddressById(String userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<Address> addressList = addressRepository.findAllByUserId(userId);
            List<AddressResponse> addressResponseList = new ArrayList<>();
            for(Address address : addressList){
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
                addressResponseList.add(addressResponse);
            }
            return addressResponseList;
        }
        return null;
    }

    @Transactional
    public AddressResponse createAddress(String userId, AddressRequest addressRequest){
        try{
            User user = userRepository.findById(userId).get();
            Address address = Address.builder()
                    .phoneNumber(addressRequest.getPhoneNumber())
                    .city(addressRequest.getCity())
                    .district(addressRequest.getDistrict())
                    .commune(addressRequest.getCommune())
                    .details(addressRequest.getDetails())
                    .user(user)
                    .build();

            Address addresssave = addressRepository.save(address);
            AddressResponse addressResponse = AddressResponse.builder()
                    .id(addresssave.getId())
                    .phoneNumber(addresssave.getPhoneNumber())
                    .city(addresssave.getCity())
                    .district(addresssave.getDistrict())
                    .commune(addresssave.getCommune())
                    .details(addresssave.getDetails())
                    .createAt(addresssave.getCreateAt())
                    .lastModify(addresssave.getLastModify())
                    .build();
            return addressResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Transactional
    public AddressResponse updateAddress(String addressId, AddressRequest addressRequest){
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if(addressOptional.isPresent()){
            Address address = addressOptional.get();
            address.setPhoneNumber(addressRequest.getPhoneNumber());
            address.setCity(addressRequest.getCity());
            address.setDistrict(addressRequest.getDistrict());
            address.setCommune(addressRequest.getCommune());
            address.setDetails(addressRequest.getDetails());

            addressRepository.save(address);
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
        else{
            return null;
        }
    }

    @Transactional
    public Boolean deleteAddress(String addressId){
        Boolean existsAddress = addressRepository.existsById(addressId);
        if(existsAddress){
            addressRepository.deleteById(addressId);
            return true;
        }
        else{
            return false;
        }
    }
}
