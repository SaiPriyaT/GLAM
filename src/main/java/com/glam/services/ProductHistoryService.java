package com.glam.services;

import java.util.List;

import com.glam.beans.Product;
import com.glam.beans.ProductHistory;

public interface ProductHistoryService {

	
	void saveProductHistory(ProductHistory productHistory);
	List<ProductHistory> getAllProductHistory();
}
