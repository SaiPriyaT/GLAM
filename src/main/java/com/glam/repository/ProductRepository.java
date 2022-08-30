package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.glam.beans.Branch;
import com.glam.beans.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT al FROM product al WHERE al.categoryId =?1")
	  List<Product> findAllProductsByCategoryId(int categoryId);
	 @Query(value = "SELECT al FROM product al WHERE al.categoryId =?1")
	  List<Product> findProductsByCategory(Integer id);
//	 @Query(value = "SELECT al FROM product al WHERE al.isActive ='Y'")
//	  List<Product> findProductsByCategoryAndisActive(Integer id);

}
