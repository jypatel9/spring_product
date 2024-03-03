package com.api.product.api.service.impl;

import com.api.product.api.model.request.GetAllProductRequest;
import com.api.product.api.model.request.GetProductRequest;
import com.api.product.api.model.request.SaveProductRequest;
import com.api.product.api.model.request.SaveProductUsingCSVRequest;
import com.api.product.api.model.response.*;
import com.api.product.api.service.ProductRepository;
import com.api.product.database.model.Product;
import com.api.product.database.repository.ProductDaoImpl;
import com.api.product.utils.Logger;
import com.api.product.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPAProductRepository implements ProductRepository {

    @Autowired
    ProductDaoImpl productDao;

    @Override
    public SaveProductResponse saveProduct(SaveProductRequest saveProductRequest) {
        SaveProductResponse saveProductResponse = new SaveProductResponse();

        Product product = new Product(saveProductRequest);
        productDao.save(product);

        saveProductResponse.setId(product.getId());
        saveProductResponse.setMessage("Product Saved SuccessFully");

        return saveProductResponse;
    }

    @Override
    public SaveProductUsingCSVResponse fetchProductUsingCSV(SaveProductUsingCSVRequest saveProductUsingCSVRequest) {
        SaveProductUsingCSVResponse saveProductUsingCSVResponse = new SaveProductUsingCSVResponse();

        List<Product> productList = null;

        try {
            productList = Utils.parseCSV(Utils.convertMultiPartToFile(saveProductUsingCSVRequest.getProduct_file()));
        } catch (Exception exception) {
            Logger.error(exception.getMessage());
            saveProductUsingCSVResponse.setCheckValidationFailed(true);
            saveProductUsingCSVResponse.setValidationMessage("Something went wrong while fetch file!");
            return saveProductUsingCSVResponse;
        }

        if (productList != null || productList.isEmpty()) {
            productDao.saveAll(productList);
        }

        saveProductUsingCSVResponse.setMessage("Product store successfully!");

        return saveProductUsingCSVResponse;
    }

    @Override
    public GetProductResponse getProduct(GetProductRequest getProductRequest) {
        GetProductResponse getProductResponse = new GetProductResponse();

        if (getProductRequest.checkBadRequest()) {
            getProductResponse.setCheckValidationFailed(true);
            getProductResponse.setValidationMessage("Bad Request");
            return getProductResponse;
        }

        Product product = productDao.findById(getProductRequest.getId()).orElse(null);

        if (product == null) {
            getProductResponse.setCheckValidationFailed(true);
            getProductResponse.setValidationMessage("Product Not Found");
            return getProductResponse;
        }

        getProductResponse.setProductDetails(new ProductDetails(product));
        return getProductResponse;
    }

    @Override
    public GetAllProductResponse getAllProduct(GetAllProductRequest getAllProductRequest) {
        GetAllProductResponse getAllProductResponse = new GetAllProductResponse();

        List<Product> productList = productDao.findAll();

        if (productList == null || productList.isEmpty()) {
            getAllProductResponse.setCheckValidationFailed(true);
            getAllProductResponse.setValidationMessage("Products Not Found!");
            return getAllProductResponse;
        }

        List<ProductDetails> productDetailsList = productList.stream()
                .map(ProductDetails::new).toList();

        getAllProductResponse.setProductDetailsList(productDetailsList);

        return getAllProductResponse;
    }
}
