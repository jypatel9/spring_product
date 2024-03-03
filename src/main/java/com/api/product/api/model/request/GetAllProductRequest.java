package com.api.product.api.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllProductRequest {

    private int skip;

    private int limit;

    private String sort_by = "product_name";

    private String sort_direction = "asc";
}
