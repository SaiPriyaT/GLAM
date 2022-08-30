package com.glam.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.glam.beans.Rating;


public interface RatingService {
	List<Rating> getAllRatings();

	Rating addNewRating(Rating rating);
	void updateRating(Rating rating);
	Rating getRatingById(int ratingID);

	}


