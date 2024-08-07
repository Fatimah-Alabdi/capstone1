package com.example.capstone1_e_commerce_website.Controller;

import com.example.capstone1_e_commerce_website.Api.ApiResponse;
import com.example.capstone1_e_commerce_website.Model.MerchantStock;
import com.example.capstone1_e_commerce_website.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/merchantstuck")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping("/get")
    public ResponseEntity getAllMerchantStock(){
        ArrayList<MerchantStock> merchantStocks=merchantStockService.getMerchantStocks();
        return ResponseEntity.status(200).body(merchantStocks);
    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid@RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String msg=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStuck(@PathVariable int id, @Valid@RequestBody MerchantStock merchantStock,Errors errors){
        if(errors.hasErrors()){
            String msg=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean status=merchantStockService.updateMerchantStock(id,merchantStock);
        if(status){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Updated"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStuck(@PathVariable int id){
        boolean status=merchantStockService.deleteMerchantStock(id);
        if(status){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Deleted"));
    }
    @PutMapping("/addstock/{productId}/{merchantId}/{amount}")
    public ResponseEntity addStock(@PathVariable int productId,@PathVariable int merchantId,@PathVariable int amount){
        boolean isaddstock= merchantStockService.addStok(productId,merchantId,amount);
        if(isaddstock){
            return ResponseEntity.status(200).body(new ApiResponse(" Stock  of product is Added"));

        }
        return ResponseEntity.status(400).body(new ApiResponse(" Stock Not Added"));

    }
}
