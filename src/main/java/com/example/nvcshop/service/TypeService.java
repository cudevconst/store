package com.example.nvcshop.service;

import com.example.nvcshop.dto.request.TypeRequest;
import com.example.nvcshop.dto.response.TypeResponse;
import com.example.nvcshop.entity.Type;
import com.example.nvcshop.mapper.TypeMapper;
import com.example.nvcshop.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {
    @Autowired
    private TypeRepository typeRepository;

//    public List<TypeResponse> findTypeByProdyctId(String productId){
//
//    }
    public List<TypeResponse> getAllTypeProduct(){
        List<Type> types = typeRepository.findAll();
        List<TypeResponse> typeRespons = new ArrayList<>();
        for(Type type : types){
            TypeResponse typeResponse = TypeMapper.toResponse(type);
            typeRespons.add(typeResponse);
        }
        return typeRespons;
    }

    @Transactional
    public TypeResponse createTypeProduct(TypeRequest typeRequest){
        Type type = Type.builder()
                .nameType(typeRequest.getNameType())
                .build();
        Type typeSave = typeRepository.save(type);
        TypeResponse typeResponse = TypeMapper.toResponse(typeSave);
        return typeResponse;
    }

    @Transactional
    public TypeResponse updateTypeProduct(String typeId, TypeRequest typeRequest){
        Optional<Type> typeProductOptional = typeRepository.findById(typeId);
        if(typeProductOptional.isPresent()){
            Type type = typeProductOptional.get();
            type.setNameType(typeRequest.getNameType());
            typeRepository.save(type);
            TypeResponse typeResponse = TypeMapper.toResponse(type);
            return typeResponse;
        }
        else{
            return null;
        }
    }

    @Transactional
    public Boolean deleteTypeProduct(String typeId){
        Optional<Type> typeOptional = typeRepository.findById(typeId);
        if(typeOptional.isPresent()){
            typeRepository.deleteById(typeId);
            return true;
        }
        else{
            return false;
        }
    }
}
