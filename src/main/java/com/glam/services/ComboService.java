package com.glam.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.glam.beans.Combo;
public interface ComboService {
	List<Combo> getAllCombos();
    Combo addCombo(Combo combos);
    Combo getComboById(int comboid);
   // Combo getServices(String careType);
    void updateComboById(Combo combos);
    void deleteComboById(int id);
    void saveCombo(Combo combos);
	void updateCombo(Combo combo);
	
}
