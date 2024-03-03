package com.api.product.api.model.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SaveProductUsingCSVRequest {

    private MultipartFile product_file;


}
