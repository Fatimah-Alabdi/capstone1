package com.example.capstone1_e_commerce_website.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    @NotNull(message = "id cannot be null")
    private int id;
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 6,message = "username must be more than 5 character")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,}$")
    @NotEmpty(message = "password cannot be empty")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;
    @NotEmpty(message = "role cannot be null")
    @Pattern(regexp = "Admin|Customer", message = "role must be either 'Admin' or 'Customer' only")
    private String role;
    @NotNull(message = "balance cannot be null")
    @Positive(message = "balance must be positive number")
    private float balance;
}
