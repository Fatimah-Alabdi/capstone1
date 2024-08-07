package com.example.capstone1_e_commerce_website.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {
    @NotNull(message = "id cannot be null")
    private int id;
    @NotEmpty(message = "name cannot be empty")
    @Size(min = 4,message = "name must be more than 3 character")
    private String name;
}
