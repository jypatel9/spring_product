package com.api.product.api.model.response;

import com.api.product.api.model.response.common.CommonApiDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAllProductResponse extends CommonApiDataResponse {

    @JsonProperty("product")
    private List<ProductDetails> productDetailsList;
}
