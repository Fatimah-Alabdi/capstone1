package com.example.capstone1_e_commerce_website.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotNull(message = "id cannot be null")
    private int id;
    @NotNull(message = "productId cannot be null")
    private int productId;
    @NotNull(message = "merchantId cannot be null")
    private int merchantId;
    @NotNull(message = "stuck cannot be null")
    @Min(value = 11, message = "the minimum number of stuck must be more than 10")
    private int stuck;

}
