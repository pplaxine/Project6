package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.consumer.contract.dao.SecteurDao;
import com.philippe75.p6.consumer.contract.dao.VoieDao;
import com.philippe75.p6.consumer.impl.rowmapper.SecteurRM;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.consumer.impl.rowmapper.VoieRM;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
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
		
		secteur.setVoies(getDaoHandler().getVoieDao().listVoie(id));
		
		return secteur;
	}

	@Override
	public List<Secteur> listSecteur(int site_id) {
		
	String sQL = "SELECT * FROM public.secteur WHERE site_id=?";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Secteur> rm = new SecteurRM();		
		
		List<Secteur> listSecteur = (List<Secteur>)jT.query(sQL, new Object[] {site_id}, rm);
		
		for (Secteur secteur : listSecteur) {
			secteur.setVoies(getDaoHandler().getVoieDao().listVoie(secteur.getId()));
		}
		
		return listSecteur;
	}
	
	



}
