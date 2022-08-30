package com.glam.services;

import java.util.List;

import com.glam.beans.Aboutus;


public interface AboutusService {
	
	List<Aboutus> getAllAbouts();

	Aboutus addAboutus(Aboutus about);

	void updateAboutus(Aboutus about);

	void deleteAboutusById(int id);

	Aboutus getAboutusById(int id);


}
