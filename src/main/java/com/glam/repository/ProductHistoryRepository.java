package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glam.beans.ProductHistory;

public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Integer> {
//	@Query("select history from producthistory care where history.action=?1")
//	ProductHistory findByAction(String action);
	
	
	
	
	

}
