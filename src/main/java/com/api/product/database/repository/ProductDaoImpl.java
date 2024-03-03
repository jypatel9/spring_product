package com.api.product.database.repository;

import com.api.product.database.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDaoImpl extends JpaRepository<Product, Integer> {
}
