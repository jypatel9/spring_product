package com.api.product.api.model.response.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CommonErrorAPIResponse {

    @JsonProperty("success")
    private int success;

    @JsonProperty("data")
    @JsonIgnore
    private Object data;

    @JsonProperty("error")
    private ArrayList<String> error;

}
