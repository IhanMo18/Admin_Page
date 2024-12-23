package com.web.proyect1.shoppingcart.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class ProductPurshased {

    @Id
    @GeneratedValue
    private Integer productPurchasedId;

    private int quantity;

    @ManyToOne
    @JoinColumn(
            name = "product_id"
    )
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "shopping_id"
    )
    @JsonBackReference
    private Shopping shopping;


    public ProductPurshased(int quantity) {
        this.quantity = quantity;
    }

    public ProductPurshased() {

    }

    public Integer getProductPurchasedId() {
        return productPurchasedId;
    }

    public void setProductPurchasedId(Integer productPurchasedId) {
        this.productPurchasedId = productPurchasedId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Shopping getShopping() {
        return shopping;
    }

    public void setShopping(Shopping shopping) {
        this.shopping = shopping;
    }
}
