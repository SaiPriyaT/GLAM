package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glam.beans.ProductCategory;
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

}
