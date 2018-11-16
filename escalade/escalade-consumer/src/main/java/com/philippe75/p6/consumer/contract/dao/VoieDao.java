package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.site.Voie;

public interface VoieDao {
	
	Voie findVoie(int id);
	
	int saveVoie(Voie voie, int id, boolean hasSecteur);
	
	List<Voie> listVoie(int secteur_id, boolean hasSecteur);
}
