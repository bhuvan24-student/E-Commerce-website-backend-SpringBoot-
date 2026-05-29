package com.backend.eco.Repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.eco.Model.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {
         
    @Query(value = """
            SELECT *
            FROM products
            WHERE name LIKE CONCAT('%', :keyword, '%')
               OR description LIKE CONCAT('%', :keyword, '%')
               OR brand LIKE CONCAT('%', :keyword, '%')
               OR category LIKE CONCAT('%', :keyword, '%')
            """, nativeQuery = true)
            List<Products> searchProducts(String keyword);
}
