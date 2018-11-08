package com.philippe75.p6.consumer.impl.dao;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.consumer.contract.dao.SecteurDao;
import com.philippe75.p6.consumer.contract.dao.VoieDao;
import com.philippe75.p6.consumer.impl.rowmapper.SecteurRM;
import com.philippe75.p6.consumer.impl.rowmapper.VoieRM;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Voie;

@Named("SecteurDao")
public class SecteurDaoImpl extends AbstractDaoImpl implements SecteurDao{
	
	@Inject
	VoieDao voieDao;

	@Override
	public Secteur findSecteur(int id) {
		
		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
				
		String sQL = "SELECT * FROM public.secteur WHERE id = ?";
		
		RowMapper<Secteur> rm = new SecteurRM();
		
		Secteur secteur = (Secteur)jT.queryForObject(sQL, new Object[] {id}, rm);
		
		secteur.setVoie(voieDao.listVoie(id));
		
		return secteur;
	}



}
