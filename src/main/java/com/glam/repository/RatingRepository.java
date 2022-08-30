package com.glam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glam.beans.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

	@Query("Select sum(r.employeeRating) from rating r where r.employeeId=?1")
	public Long sumOfRating(int employeeId);

	@Query("select count(al) from rating al where al.employeeId=?1 and al.employeeRating=5")
	public int fivecount(int employeeId);

	@Query("select count(al)from rating al where al.employeeId=?1 and al.employeeRating=4")
	public int fourcount(int employeeId);

	@Query("select count(al) from rating al where al.employeeId=?1 and al.employeeRating=3")
	public int threecount(int employeeId);

	@Query("select count(al) from rating al where al.employeeId=?1 and al.employeeRating=2")
	public int twocount(int employeeId);

	@Query("select count(al) from rating al where al.employeeId=?1 and al.employeeRating=1")
	public int onecount(int employeeId);

	
	  @Query(value = "SELECT *\r\n" + " FROM public.rating\r\n" +
	  " order by created_date DESC;", nativeQuery = true) List<Rating>
	  findAllRatingsInDescOrder();
	 

	@Query("Select al from rating al where al.employeeId=?1")
	List<Rating> emprating(int employeeId);

}
