package com.example.capstone1_e_commerce_website.Service;

import com.example.capstone1_e_commerce_website.Model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;


@Service
public class UserService {
    private final ProductService productService;
    private final MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();
    private final ArrayList<String> reviews = new ArrayList<>();
    private final ArrayList<String> transactionHistory = new ArrayList<>();


    public UserService(ProductService productService, MerchantService merchantService, MerchantStockService merchantStockService) {
        this.productService = productService;
        this.merchantService = merchantService;
        this.merchantStockService = merchantStockService;
    }


    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(int id, User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public String buyProduct(int userId, int productId, int merchantId) {
        User user = null;
        Product product = null;
        Merchant merchant = null;
        MerchantStock merchantStock = null;


        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                user = users.get(i);

            }
        }
        if (user == null) {
            return "1";
        }
        if (!user.getRole().equalsIgnoreCase("Customer")) {
            return "6";
        }

        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == productId) {
                product = productService.products.get(i);
            }
        }
        if (product == null) {
            return "2";
        }

        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId() == merchantId) {
                merchant = merchantService.merchants.get(i);
            }
        }
        if (merchant == null) {
            return "3";
        }

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            MerchantStock stock = merchantStockService.merchantStocks.get(i);
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                merchantStock = stock;

            }


        }
        if (merchantStock == null || merchantStock.getStuck() == 0) {
            return "4";
        }
        if (user.getBalance() < product.getPrice()) {
            return "5";
        }
        merchantStock.setStuck(merchantStock.getStuck() - 1);
        user.setBalance(user.getBalance() - product.getPrice());

        String transaction = userId + "-" + productId + "-" + merchantId;
        transactionHistory.add(transaction);
        return "true";

    }

    public String applyDiscount(int userId, int productId, int merchantId, float discountPercentage) {
        User user = null;
        Product product = null;
        MerchantStock merchantStock = null;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                user = users.get(i);

            }
        }
        if (user == null) {
            return "1";
        }
        if (!user.getRole().equalsIgnoreCase("Admin")) {
            return "2";
        }
        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == productId) {
                product = productService.products.get(i);

            }
        }
        if (product == null) {
           return "3";
        }

        for (int i = 0; i <merchantStockService.merchantStocks.size(); i++) {
            if (merchantStockService.merchantStocks.get(i).getId() == merchantId) {
                merchantStock=merchantStockService.merchantStocks.get(i);

            }
        }
if (merchantStock == null ) {
    return "4";
}
        if (discountPercentage <= 0 || discountPercentage > 100) {
            return "5";
        }
        float originalPrice = product.getPrice();
        float discountAmount = (originalPrice * discountPercentage) / 100;
        float discountedPrice = originalPrice - discountAmount;

product.setPrice(discountedPrice);


        return "true";

    }
    public String returnProduct(int userId, int productId, int merchantId) {
        User user = null;
        Product product = null;
        Merchant merchant = null;
        MerchantStock merchantStock = null;


        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                user = users.get(i);

            }
        }

            if (user == null) {
                return "1";
            }
            if (!user.getRole().equalsIgnoreCase("Customer")) {
                return "4";
            }



        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == productId) {
                product = productService.products.get(i);
            }
        }
        if (product == null) {
            return "2";
        }

        for (int i = 0; i < merchantService.merchants.size(); i++) {
            if (merchantService.merchants.get(i).getId() == merchantId) {
                merchant = merchantService.merchants.get(i);
            }
        }
        if (merchant == null) {
            return "3";
        }

        for (int i = 0; i < merchantStockService.merchantStocks.size(); i++) {
            MerchantStock stock = merchantStockService.merchantStocks.get(i);
            if (stock.getProductId() == productId && stock.getMerchantId() == merchantId) {
                merchantStock = stock;

            }


    }
        String transaction = userId + "-" + productId + "-" + merchantId;
        if (!transactionHistory.contains(transaction)) {
            return "7";
        }
        transactionHistory.remove(transaction);

            merchantStock.setStuck(merchantStock.getStuck() + 1);
        user.setBalance(user.getBalance() + product.getPrice());
        return "true";
    }
    public String addReview(int userId, int productId, int rating,String comment) {
        User user = null;
        Product product = null;
        boolean hasPurchased = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                user = users.get(i);

            }
        }

        if (user == null) {
            return "1";
        }
        if (!user.getRole().equalsIgnoreCase("Customer")) {
            return "2";
        }


        for (int i = 0; i < productService.products.size(); i++) {
            if (productService.products.get(i).getId() == productId) {
                product = productService.products.get(i);
            }
        }
        if (product == null) {
            return "4";
        }
        if (rating < 1||rating > 5) {
            return "3";
        }

        String transaction = userId + "-" + productId;


        for(int i=0;i<transactionHistory.size();i++){
            if(transactionHistory.get(i).contains(transaction)){
                 hasPurchased = true;
            }
        }
        if(!hasPurchased){
            return "5";
        }

        String review = "User " + userId + " : product " + productId + " rated : " + rating + " : " + comment;
        reviews.add(review);
        return "true";

    }
    public ArrayList<String> getReviews(int productId) {
        ArrayList<String> previews = new ArrayList<>();

        for (int i = 0; i < reviews.size(); i++) {
            if (productService.products.get(i).getId() == productId) {
                previews.add(Arrays.toString(reviews.get(i).split(",")));

            }
        }
       if (previews.isEmpty()){
           return null;
       }
       return previews;


    }
}
