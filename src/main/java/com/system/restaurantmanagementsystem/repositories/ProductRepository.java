package com.system.restaurantmanagementsystem.repositories;

import com.system.restaurantmanagementsystem.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategoryId(Long categoryId);

    List<Product>  findAllByCategoryIdAndNameContaining(Long categoryId, String title);
}
