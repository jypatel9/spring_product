package com.api.product.utils;

import com.api.product.api.model.response.common.CommonErrorAPIResponse;
import com.api.product.api.model.response.common.CommonSuccessAPIResponse;
import com.api.product.database.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Utils {

    public static JsonNode generateSuccessResponse(Object data) {
        CommonSuccessAPIResponse commonSuccessAPIResponse = new CommonSuccessAPIResponse();
        commonSuccessAPIResponse.setSuccess(1);
        commonSuccessAPIResponse.setData(data);
        commonSuccessAPIResponse.setError(null);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.convertValue(commonSuccessAPIResponse, JsonNode.class);
    }

    public static JsonNode generateErrorResponse(String message) {
        CommonErrorAPIResponse commonErrorAPIResponse = new CommonErrorAPIResponse();
        commonErrorAPIResponse.setSuccess(0);
        commonErrorAPIResponse.setData(null);
        ArrayList<String> errors = new ArrayList<>();
        errors.add(message);
        commonErrorAPIResponse.setError(errors);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper.convertValue(commonErrorAPIResponse, JsonNode.class);
    }

    public static List<Product> parseCSV(File file) {
        List<Product> products = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            boolean firstLine = true;
            while ((line = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                Product product = new Product();
                product.setProductName(line[0]);
                product.setProductQuantity(Double.parseDouble(line[1]));
                product.setBrandName(line[2]);
                products.add(product);
            }
        } catch (IOException | CsvValidationException ioException) {
            Logger.error(ioException.getMessage());
        }
        return products;
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static Pageable getPageable(int skip, int limit) {
        return PageRequest.of(skip, limit);
    }

}
