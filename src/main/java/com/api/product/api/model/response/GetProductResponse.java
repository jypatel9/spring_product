package com.api.product.api.model.response;

import com.api.product.api.model.response.common.CommonApiDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetProductResponse extends CommonApiDataResponse {

    @JsonProperty("product")
    private ProductDetails productDetails;
}
