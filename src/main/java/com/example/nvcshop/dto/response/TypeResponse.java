package com.example.nvcshop.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TypeResponse {
    private String id;
    private String nameType;
    private Date createAt;
    private Date lastModify;
}
