package com.api.product.api.service;

import com.api.product.api.model.request.GetProductRequest;
import com.api.product.api.model.request.SaveProductRequest;
import com.api.product.api.model.response.GetAllProductResponse;
import com.api.product.api.model.response.SaveProductResponse;
import com.api.product.api.model.request.GetAllProductRequest;
import com.api.product.api.model.request.SaveProductUsingCSVRequest;
import com.api.product.api.model.response.GetProductResponse;
import com.api.product.api.model.response.SaveProductUsingCSVResponse;

public interface ProductRepository {

    SaveProductResponse saveProduct(SaveProductRequest saveProductRequest);

    SaveProductUsingCSVResponse fetchProductUsingCSV(SaveProductUsingCSVRequest saveProductUsingCSVRequest);

    GetProductResponse getProduct(GetProductRequest getProductRequest);

    GetAllProductResponse getAllProduct(GetAllProductRequest getAllProductRequest);
}
