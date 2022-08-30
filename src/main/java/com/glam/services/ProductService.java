package com.glam.services;

import java.util.List;

import com.glam.beans.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	List<Product> getAllProductsBycategoryId(int categoryid);


	void saveProduct(Product product);
	

	Product getProductById(int productid);

	void deleteProductById(int productid);
	
	 List<Product> getPtoductsByCategory(Integer id);

}
