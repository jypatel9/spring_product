package com.api.product.api.controller;

import com.api.product.api.model.request.GetAllProductRequest;
import com.api.product.api.model.request.GetProductRequest;
import com.api.product.api.model.request.SaveProductRequest;
import com.api.product.api.model.request.SaveProductUsingCSVRequest;
import com.api.product.api.model.response.GetAllProductResponse;
import com.api.product.api.model.response.GetProductResponse;
import com.api.product.api.model.response.SaveProductResponse;
import com.api.product.api.model.response.SaveProductUsingCSVResponse;
import com.api.product.api.service.ProductRepository;
import com.api.product.utils.Utils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("${app.baseurl}")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("product")
    public ResponseEntity<JsonNode> saveProduct(@RequestBody SaveProductRequest saveProductRequest) {

        if (saveProductRequest.checkBadRequest()) {
            return new ResponseEntity<>(Utils.generateErrorResponse("BAD_REQUEST"), HttpStatus.OK);
        }

        SaveProductResponse saveProductResponse = productRepository.saveProduct(saveProductRequest);

        return new ResponseEntity<>(Utils.generateSuccessResponse(saveProductResponse), HttpStatus.OK);
    }

    @GetMapping("product_csv")
    public ResponseEntity<JsonNode> fetchProductUsingCSV(@RequestParam MultipartFile product_file) {
        SaveProductUsingCSVRequest saveProductUsingCSVRequest = new SaveProductUsingCSVRequest();
        saveProductUsingCSVRequest.setProduct_file(product_file);

        SaveProductUsingCSVResponse saveProductUsingCSVResponse = productRepository.fetchProductUsingCSV(saveProductUsingCSVRequest);

        if (saveProductUsingCSVResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(Utils.generateErrorResponse(saveProductUsingCSVResponse.getValidationMessage()), HttpStatus.OK);
        }

        return new ResponseEntity<>(Utils.generateSuccessResponse(saveProductUsingCSVResponse), HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<JsonNode> getProduct(@PathVariable Integer id, @ModelAttribute GetProductRequest getProductRequest) {
        getProductRequest.setId(id);

        GetProductResponse getProductResponse = productRepository.getProduct(getProductRequest);

        if (getProductResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(Utils.generateErrorResponse(getProductResponse.getValidationMessage()), HttpStatus.OK);
        }

        return new ResponseEntity<>(Utils.generateSuccessResponse(getProductResponse), HttpStatus.OK);
    }

    @GetMapping("product")
    public ResponseEntity<JsonNode> getAllProduct(@ModelAttribute GetAllProductRequest getAllProductRequest) {
        GetAllProductResponse getAllProductResponse = productRepository.getAllProduct(getAllProductRequest);

        if (getAllProductResponse.isCheckValidationFailed()) {
            return new ResponseEntity<>(Utils.generateErrorResponse(getAllProductResponse.getValidationMessage()), HttpStatus.OK);
        }

        return new ResponseEntity<>(Utils.generateSuccessResponse(getAllProductResponse), HttpStatus.OK);
    }
}
