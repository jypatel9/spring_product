package com.api.product.database.model;

import com.api.product.api.model.request.SaveProductRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    @JsonProperty("product_name")
    private String productName;

    @Column(name = "product_quantity")
    @JsonProperty("product_quantity")
    private Double productQuantity;

    @Column(name = "brand_name")
    @JsonProperty("brand_name")
    private String brandName;

    public Product(SaveProductRequest saveProductRequest) {
        this.setProductName(saveProductRequest.getProduct_name());
        this.setBrandName(saveProductRequest.getBrand_name());
        this.setProductQuantity(saveProductRequest.getProduct_quantity());
    }

}
