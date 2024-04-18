package com.example.nvcshop.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String email;
    private String password;
    private String fullName;
}
