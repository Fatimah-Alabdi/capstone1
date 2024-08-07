package com.example.capstone1_e_commerce_website.Service;

import com.example.capstone1_e_commerce_website.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }
    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }
    public boolean updateMerchantStock(int id,MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }
    public boolean deleteMerchantStock(int id) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }
    public boolean addStok(int productId,int merchantId,int amount){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getProductId() == productId){
                if(merchantStocks.get(i).getMerchantId() == merchantId){

                    merchantStocks.get(i).setStuck(merchantStocks.get(i).getStuck()+amount);
                    return true;
                }
            }
        }
        return false;
    }
}
