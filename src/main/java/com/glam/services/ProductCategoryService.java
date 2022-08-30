package com.glam.services;

import java.util.List;

import com.glam.beans.ProductCategory;

public interface ProductCategoryService {
	List<ProductCategory> getAllProductCategories();

	ProductCategory saveProductCategory(ProductCategory productCategory);

	ProductCategory getProductCategoryById(int categoryid);
 
}
