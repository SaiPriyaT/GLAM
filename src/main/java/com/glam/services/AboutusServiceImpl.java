package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Aboutus;
import com.glam.repository.AboutusRepository;

@Service
public class AboutusServiceImpl implements AboutusService {
	
	@Autowired
	
	AboutusRepository aboutusRepository;

	@Override
	public List<Aboutus> getAllAbouts() {
		// TODO Auto-generated method stub
		return aboutusRepository.findAll();
	}

	@Override
	public Aboutus addAboutus(Aboutus about) {
		// TODO Auto-generated method stub
		return aboutusRepository.save(about);
	}

	@Override
	public void updateAboutus(Aboutus about) {
		// TODO Auto-generated method stub
		aboutusRepository.save(about);
	}

	@Override
	public void deleteAboutusById(int id) {
		// TODO Auto-generated method stub
		aboutusRepository.deleteById(id);
	}

	@Override
	public Aboutus getAboutusById(int id) {
		// TODO Auto-generated method stub
		return aboutusRepository.findById(id).get();
	}

}
