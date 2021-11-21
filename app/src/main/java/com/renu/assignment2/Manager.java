package com.renu.assignment2;

import java.util.ArrayList;

public class Manager {

    //collection class
    ArrayList<ProductItem> productItemList = new ArrayList<>(3);
    ArrayList<ProductItem> HistoryItemList = new ArrayList<>();

    public Manager(){

    }

    void checkArray(){
        System.out.println(productItemList);
    }

    boolean checkquantity(int index, int productQnt) {
        if(productItemList != null && productItemList.size() > 0){
            if(productItemList.get(index).getProductQnt() <= productQnt)
                return true;
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void createHistory(int selectePosition, String text) {
        ProductItem item = new ProductItem();
        item.setProductQnt(Integer.parseInt(text));
        item.setProductItemName(productItemList.get(selectePosition).getProductItemName());
        item.setProductPrice(productItemList.get(selectePosition).getProductPrice());
        item.setPurchaseDate(GlobalMethod.getHistoryDate());
        HistoryItemList.add(item);
        System.out.println(HistoryItemList.size());
    }

    public void addAll() {
        ProductItem pants = new ProductItem("Pants", 20.44, 10);
        ProductItem shoes = new ProductItem("Shoes", 10.44, 100);
        ProductItem hats = new ProductItem("Hats", 5.9, 30);

        //void addToArray() {
        this.productItemList.add(pants);
        this.productItemList.add(shoes);
        this.productItemList.add(hats);

    }
}
