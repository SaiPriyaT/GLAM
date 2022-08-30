package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.ProductCategory;
import com.glam.repository.ProductCategoryRepository;

@Service
public class ProductCategoryServieImpl implements ProductCategoryService{
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	@Override
	public List<ProductCategory> getAllProductCategories() {
		// TODO Auto-generated method stub
		return productCategoryRepository.findAll() ;
	}

	@Override
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		// TODO Auto-generated method stub
		return productCategoryRepository.save(productCategory);
	}

	@Override
	public ProductCategory getProductCategoryById(int categoryid) {
		// TODO Auto-generated method stub
		return productCategoryRepository.getById(categoryid);
	}
	

}
