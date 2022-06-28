package com.bridgelabz.bookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String emailId;
    private String password;

    public LoginDTO() {
        super();
    }
}
