package com.philippe75.p6.consumer.impl.dao;

import java.sql.Types;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
	
	@Override
	public int getSecteurId(String nomSecteur, int site_id) {
		
		String sQL = "SELECT secteur.id FROM public.secteur WHERE nom = :nom AND site_id = :site_id";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("nom", nomSecteur );
		mSPS.addValue("site_id", site_id);
		
		int secteur_id = nPJT.queryForObject(sQL,mSPS, Integer.class);
		
		return secteur_id;
	}

	@Override
	public int saveSecteur(Secteur secteur, int site_id) {
		
	String sQL = "INSERT INTO secteur (nom, site_id) VALUES (:nom, :site_id); COMMIT";
		
		if(secteur != null && site_id != 0) {
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();
			mSPS.addValue("nom", secteur.getNom());
			mSPS.addValue("site_id", site_id);
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			int result = nPJT.update(sQL, mSPS);
			
			if(result != 0 && secteur.getVoies() != null) {
				int secteur_id = getSecteurId(secteur.getNom(), site_id); // récupération du nom du secteur 
						
				for (Voie voie : secteur.getVoies()) {
					getDaoHandler().getVoieDao().saveVoie(voie, secteur_id); 
				}
					
				return result;
			}
			return 0;
		}
		return 0;
	}
	
	



}
