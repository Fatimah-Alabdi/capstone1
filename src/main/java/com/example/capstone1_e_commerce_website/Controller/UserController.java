package com.example.capstone1_e_commerce_website.Controller;

import com.example.capstone1_e_commerce_website.Api.ApiResponse;
import com.example.capstone1_e_commerce_website.Model.User;
import com.example.capstone1_e_commerce_website.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        ArrayList<User> users = userService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);

        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }

        boolean isUbdate = userService.updateUser(id, user);
        if (isUbdate) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User update failed"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {

        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("User delete failed"));

    }

    @PutMapping("/puyProduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        String isBuy = userService.buyProduct(userId, productId, merchantId);
        if (isBuy.equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("User buy product successfully"));
        }
        if (isBuy.equals("1")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(user id not found)"));
        }
        if (isBuy.equals("6")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(customer can buy only)"));

        }
        if (isBuy.equals("3")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(merchant id not found)"));

        }
        if (isBuy.equals("4")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(merchant Stock cannot be 0 or null)"));

        }
        if (isBuy.equals("5")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(the balance must be greater than or equal the price)"));

        }
        if (isBuy.equals("2")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(product id not found)"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("User buy product failed"));

    }

    @PutMapping("/discount/{userId}/{productId}/{merchantId}/{discount}")
    public ResponseEntity applyDiscount(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId, @PathVariable float discount) {
        String isDiscount = userService.applyDiscount(userId, productId, merchantId, discount);
        if (isDiscount.equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Admin apply discount successfully"));
        }
        if (isDiscount.equals("1")) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin apply discount failed(user id not found)"));

        }
        if (isDiscount.equals("2")) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin apply discount failed(the user must be Admin)"));
        }
        if (isDiscount.equals("3")) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin apply discount failed(product id not found)"));

        }
        if (isDiscount.equals("4")) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin apply discount failed(merchant Stock not found)"));

        }
        if (isDiscount.equals("5")) {
            return ResponseEntity.status(400).body(new ApiResponse("Admin apply discount failed(discount percentage must be between 0 and 100)"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Admin apply discount failed"));


    }
@PutMapping("/return/{userId}/{productId}/{merchantId}")
    public ResponseEntity returnProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId) {
        String isReturn = userService.returnProduct(userId, productId, merchantId);
        if (isReturn.equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("Costumer return product successfully"));
        }
        if (isReturn.equals("1")) {
            return ResponseEntity.status(400).body(new ApiResponse("User return product failed(user id not found)"));

        }
        if (isReturn.equals("4")) {
            return ResponseEntity.status(400).body(new ApiResponse("User return product failed(customer can return only)"));

        }
        if (isReturn.equals("3")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(merchant id not found)"));
        }
        if (isReturn.equals("2")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(product  id not found)"));

        }
        if (isReturn.equals("7")) {
            return ResponseEntity.status(400).body(new ApiResponse("User buy product failed(the product was not bought by the user)"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("User return product failed"));


    }
    @PostMapping("/addreview")
    public ResponseEntity addReview(@RequestParam int userId, @RequestParam int productId, @RequestParam int rating, @RequestParam String comment) {
        String isAddReview = userService.addReview(userId, productId, rating, comment);
        if (isAddReview.equals("true")) {
            return ResponseEntity.status(200).body(new ApiResponse("User add review successfully"));
        }
        if (isAddReview.equals("1")) {
            return ResponseEntity.status(400).body(new ApiResponse("User review failed(user not found)"));
        }
        if (isAddReview.equals("2")) {
            return ResponseEntity.status(400).body(new ApiResponse("User review failed(only customer can add review)"));
        }
        if (isAddReview.equals("3")) {
            return ResponseEntity.status(400).body(new ApiResponse("User review failed(Invalid rating)"));
        }
        if (isAddReview.equals("4")) {
            return  ResponseEntity.status(400).body(new ApiResponse("User review failed(Product not found)"));
        }
        if (isAddReview.equals("5")) {
            return  ResponseEntity.status(400).body(new ApiResponse("User review failed(User has not purchased the product)"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("User review failed"));
    }
    @GetMapping("/getreview/{productId}")
    public ResponseEntity getReviewProduct( @PathVariable int productId) {
        ArrayList<String> rlist = userService.getReviews(productId);
        if (rlist==null) {
            return ResponseEntity.status(400).body(new ApiResponse("Review not found"));

        }
        return ResponseEntity.status(200).body(rlist);
    }
}

