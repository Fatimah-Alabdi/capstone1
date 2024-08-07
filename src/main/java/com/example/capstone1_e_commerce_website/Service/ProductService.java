package com.example.capstone1_e_commerce_website.Service;

import com.example.capstone1_e_commerce_website.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);

    }
    public boolean updateProduct(int id,Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;

    }
    public boolean deleteProduct(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }
public ArrayList<Product> getProductsByCategory(int categoryId) {
        ArrayList<Product> productsByCategory = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getCategoryID()==categoryId){
                productsByCategory.add(products.get(i));


            }
        }
        if(productsByCategory.isEmpty()){
            return null;
        }
        return productsByCategory;

}
}
