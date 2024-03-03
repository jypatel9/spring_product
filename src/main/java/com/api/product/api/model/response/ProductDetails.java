package com.api.product.api.model.response;

import com.api.product.database.model.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetails {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_quantity")
    private Double productQuantity;

    @JsonProperty("brand_name")
    private String brandName;

    public ProductDetails(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productQuantity = product.getProductQuantity();
        this.brandName = product.getBrandName();
    }
}
