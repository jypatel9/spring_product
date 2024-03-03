package com.api.product.api.model.response.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonSuccessAPIResponse {

    @JsonProperty("success")
    private int success;

    @JsonProperty("data")
    private Object data;

    @JsonProperty("error")
    @JsonIgnore
    private ArrayList<String> error;

}
