package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.ProductHistory;
import com.glam.repository.ProductHistoryRepository;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {

	@Autowired
	private ProductHistoryRepository productHistoryRepository;
	
	@Override
	public void saveProductHistory(ProductHistory productHistory) {
		// TODO Auto-generated method stub
		this.productHistoryRepository.save(productHistory);
	}

	@Override
	public List<ProductHistory> getAllProductHistory() {
		// TODO Auto-generated method stub
		return productHistoryRepository.findAll();
	}
	

}
