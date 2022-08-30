package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Branch;
import com.glam.beans.Product;
import com.glam.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
@Autowired
private ProductRepository productRepository;

@Override
public List<Product> getAllProducts() {
	// TODO Auto-generated method stub
	return productRepository.findAll();
}

@Override
public List<Product> getAllProductsBycategoryId(int categoryid) {
	// TODO Auto-generated method stub
	return productRepository.findAllProductsByCategoryId(categoryid);
}

@Override
public void saveProduct(Product product) {
	// TODO Auto-generated method stub
	this.productRepository.save(product);
	
}

@Override
public Product getProductById(int productid) {
	// TODO Auto-generated method stub
	return productRepository.getById(productid);
}

@Override
public void deleteProductById(int productid) {
	// TODO Auto-generated method stub
	this.productRepository.deleteById(productid);
}

@Override
public List<Product> getPtoductsByCategory(Integer id) {
	// TODO Auto-generated method stub
	return productRepository.findProductsByCategory(id);
}


	

}
