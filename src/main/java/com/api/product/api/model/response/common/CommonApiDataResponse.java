package com.api.product.api.model.response.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"validationMessage", "checkValidationFailed"})
public class CommonApiDataResponse {

    @JsonProperty("validationMessage")
    private String validationMessage;

    @JsonProperty("checkValidationFailed")
    private boolean checkValidationFailed;

    @JsonProperty("message")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;


}
