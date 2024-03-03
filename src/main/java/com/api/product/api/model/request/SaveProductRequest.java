package com.api.product.api.model.request;

import com.mysql.cj.util.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveProductRequest {

    private String product_name;

    private Double product_quantity;

    private String brand_name;

    public boolean checkBadRequest() {
        return StringUtils.isEmptyOrWhitespaceOnly(this.getProduct_name());
    }

}
