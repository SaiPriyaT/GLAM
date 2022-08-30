package com.glam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.glam.beans.Branch;
import com.glam.beans.OurCare;
@Repository
public interface OurCareRepository extends JpaRepository<OurCare, Integer> {
//	@Query("SELECT care FROM our_care care\r\n"
//			+ "WHERE care.storeid in (SELECT storeid FROM store WHERE storeid = :storeid)\r\n"
//			+ " AND care.branchid in (SELECT branchid FROM branch WHERE branchid= :branchid)")
	
	
//	@Query("SELECT care FROM our_care care JOIN care.store store")
//	List<OurCare> findAllTables();
	
	@Query("SELECT care FROM our_care care where upper(care.careType)=upper(?1)")
	List<OurCare> findAllByCareType(String careType);
	
	@Query("select care from our_care care where care.careID=?1" )
	OurCare findByCareId(int careID);
	
	@Query("select care from our_care care where care.careID=?1")
    OurCare findByCareId(String careID);
	
	@Query("SELECT care FROM our_care care")
	List<OurCare> findAllList();
	
	@Query("select care from our_care care where concat(care.careType,'  ',care.careName,'  ',care.careCategory) like %?1%" )
	Page<OurCare> findAll(String keyword,Pageable pageable);
	
	
	  @Query(value = "SELECT care FROM our_care care WHERE care.storeID =?1 and care.branchID=?2")
	  List<OurCare> findAllServicesByStoreID(int storeid,int branchid);
	  
	  @Query("select distinct upper(careType) from our_care")
	  List<String> findDistinctCare(String careType);

	  @Query("select care from our_care care where care.careName=?1")
	  List<OurCare> findService(String careName);
	  
	  @Query("select distinct upper(careType) from our_care care WHERE care.storeID =?1 and care.branchID=?2")
		 List<String> findAllDistinctServicesByBranchID( int storeid, int branchid);

	  @Query("select distinct upper(careType) from our_care care where care.storeID=?1 and care.branchID=?2")
	  List<String> findDistinctCareAndBranch(int storeid,int branchid);
}
