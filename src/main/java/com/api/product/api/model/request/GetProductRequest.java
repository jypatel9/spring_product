package com.api.product.api.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class GetProductRequest {

    private Integer id;

    public boolean checkBadRequest() {
        return Objects.isNull(this.getId());
    }


}
