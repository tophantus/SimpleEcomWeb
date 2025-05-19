package com.example.ecomProj.repository;

import com.example.ecomProj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where " +
            "lower(p.name) Like lower(concat('%', :keyword, '%')) or " +
            "lower(p.brand) Like lower(concat('%', :keyword, '%')) or " +
            "lower(p.category) Like lower(concat('%', :keyword, '%'))"
    )
    List<Product> searchProducts(String keyword);
}
