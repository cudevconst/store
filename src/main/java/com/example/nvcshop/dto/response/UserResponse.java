package com.example.nvcshop.dto.response;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String email;
    private String fullName;
    private Date createAt;
    private Date lastModify;
}
