package com.example.nvcshop.mapper;

import com.example.nvcshop.dto.request.TypeRequest;
import com.example.nvcshop.dto.response.TypeResponse;
import com.example.nvcshop.entity.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeMapper {
    public static TypeResponse toResponse(Type type){
        TypeResponse typeResponse = TypeResponse.builder()
                .id(type.getId())
                .nameType(type.getNameType())
                .createAt(type.getCreateAt())
                .lastModify(type.getLastModify())
                .build();
        return typeResponse;
    }
    public static List<TypeResponse> toListResponse(List<Type> types){
        List<TypeResponse> typeResponses = new ArrayList<>();
        for(Type t : types){
            TypeResponse typeResponse = TypeResponse.builder()
                    .id(t.getId())
                    .nameType(t.getNameType())
                    .createAt(t.getCreateAt())
                    .lastModify(t.getLastModify())
                    .build();
            typeResponses.add(typeResponse);
        }
        return typeResponses;
    }

    public static Type toEntity(TypeRequest typeRequest){
        Type type = Type.builder()
                .nameType(typeRequest.getNameType())
                .build();
        return type;
    }
}
