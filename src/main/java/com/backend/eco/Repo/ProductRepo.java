package com.backend.eco.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.eco.Model.Products;
@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {
}
