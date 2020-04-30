package com.ProductManager.ProjectWebMaster.models;

/* Creation of collections and indexations of attributes */
/* In this class we stock all products */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Products {
    @Id
    String id;
    String prodName;
    String prodDesc;
    Double prodPrice;

    public Products(){}

    public Products(String prodName, String prodDesc, Double prodPrice){
        super();
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodPrice = prodPrice;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", prodName='" + prodName + '\'' +
                ", prodDesc='" + prodDesc + '\'' +
                ", prodPrice=" + prodPrice +
                '}';
    }
}
