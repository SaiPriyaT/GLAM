package com.glam.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glam.beans.Combo;
import com.glam.repository.ComboRepository;



@Service
public class ComboServiceImpl implements ComboService {
@Autowired
private ComboRepository comboRepository;
	@Override
	public List<Combo> getAllCombos() {
		// TODO Auto-generated method stub
		return comboRepository.findAll();
	}

	@Override
	public Combo addCombo(Combo combos) {
		// TODO Auto-generated method stub
		return comboRepository.save(combos);
	}

	@Override
	public Combo getComboById(int comboid) {
		// TODO Auto-generated method stub
		return comboRepository.getById(comboid);
	}

	@Override
	public void updateComboById(Combo combos) {
		// TODO Auto-generated method stub
		this.comboRepository.save(combos);
	}

	@Override
	public void deleteComboById(int id) {
		// TODO Auto-generated method stub
		this.comboRepository.deleteById(id);
	}

	@Override
	public void saveCombo(Combo combos) {
		
     this.comboRepository.save(combos);
		
	}

	@Override
	public void updateCombo(Combo combo) {
		this.comboRepository.save(combo);
		
	}

	/*
	 * @Override public Combo getServices(String careType) { // TODO Auto-generated
	 * method stub return null; }
	 */
}
