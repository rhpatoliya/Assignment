package com.renu.assignment2;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class ProductItem implements Parcelable, Serializable {
    public String productItemName;
    public int productQnt;
    public Double productPrice;

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public static Creator<ProductItem> getCREATOR() {
        return CREATOR;
    }

    public String purchaseDate;

    public ProductItem() {

    }

    protected ProductItem(Parcel in) {
        productItemName = in.readString();
        purchaseDate = in.readString();
        productQnt = in.readInt();
        if (in.readByte() == 0) {
            productPrice = null;
        } else {
            productPrice = in.readDouble();
        }
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

    public String getProductItemName() {
        return productItemName;
    }

    public void setProductItemName(String productItemName) {
        this.productItemName = productItemName;
    }

    public int getProductQnt() {
        return productQnt;
    }

    public void setProductQnt(int productQnt) {
        this.productQnt = productQnt;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public ProductItem(String productItemName, double productPrice, int productQnt) {
        this.productItemName = productItemName;
        this.productPrice = productPrice;
        this.productQnt = productQnt;


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productItemName);
        dest.writeString(purchaseDate);
        dest.writeInt(productQnt);
        if (productPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(productPrice);
        }
    }


}
