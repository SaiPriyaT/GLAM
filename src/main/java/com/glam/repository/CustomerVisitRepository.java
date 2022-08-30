package com.glam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.CustomerVisit;


@Repository
public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Integer>{
	
	@Query("select al from customer_visit al where lower(al.customerEmailID) = lower(?1) and al.password = ?2")
	CustomerVisit findByCustomeremailandpassword(String customerEmailID,String password);
	
	
@Query("select al.customerEmailID from customer_visit al where al.customerEmailID=?1")
String findByemailIgnoreCase(String customerEmailID);
@Query("select a from customer_visit a WHERE lower(a.customerEmailID)=lower(?1)")
public CustomerVisit findByEmail(String customerEmailID);
}