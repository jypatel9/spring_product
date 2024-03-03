package com.api.product.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveProductResponse {

    @JsonProperty("product_id")
    private Integer id;

    @JsonProperty("message")
    private String message;
}
