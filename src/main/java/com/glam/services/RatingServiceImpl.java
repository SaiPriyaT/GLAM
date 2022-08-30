package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Rating;
import com.glam.repository.RatingRepository;
@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepository ratingRepository;
	@Override
	public List<Rating> getAllRatings() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	public Rating addNewRating(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	public void updateRating(Rating rating) {
		// TODO Auto-generated method stub
		this.ratingRepository.save(rating);
	}

	@Override
	public Rating getRatingById(int ratingID) {
		// TODO Auto-generated method stub
		return ratingRepository.getById(ratingID);
	}

}
