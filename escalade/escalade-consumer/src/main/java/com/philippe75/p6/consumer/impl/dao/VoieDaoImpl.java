package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.VoieDao;
import com.philippe75.p6.consumer.impl.rowmapper.VoieRM;
import com.philippe75.p6.model.bean.site.Voie;

@Named("voieDao")
public class VoieDaoImpl extends AbstractDaoImpl implements VoieDao {

	@Override
	public Voie findVoie(int id) {
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
				
		String sQL = "SELECT * FROM public.voie WHERE id=?";
		
		RowMapper<Voie> rm = new VoieRM();
		
		try {
			Voie voie = (Voie)jT.queryForObject(sQL, new Object[] {id}, rm);
			voie.setCotation(getDaoHandler().getCotationDao().findCotation(id) );
			
			return voie;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return null;
		} 
	}
	

	@Override
	public List<Voie> listVoie(int id, boolean hasSecteur) {
		
		String sQLSecteur = "SELECT * FROM public.voie WHERE secteur_id=?";		
		String sQLSite = "SELECT * FROM public.voie WHERE site_id=?";
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		List<Voie> listVoie;
		RowMapper<Voie> rm = new VoieRM();		
		
		if(hasSecteur) {
			listVoie = (List<Voie>)jT.query(sQLSecteur, new Object[] {id}, rm);
			for (Voie voie : listVoie) {
				voie.setCotation(getDaoHandler().getCotationDao().findCotation(voie.getId()));
			}
		}else {
			 listVoie = (List<Voie>)jT.query(sQLSite, new Object[] {id}, rm);
			for (Voie voie : listVoie) {
				voie.setCotation(getDaoHandler().getCotationDao().findCotation(voie.getId()));
			}
		}
		return listVoie;
	}

	@Override
	public int saveVoie(Voie voie, int id, boolean hasSecteur ) {
		
		if(voie != null && id != 0){
			
			String sQLVoieSite = "INSERT INTO voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id, site_id) VALUES (:nom, :hauteur, :nombre_points, :nombre_longueurs, :cotation_id, :site_id); COMMIT";
			String sQLVoieSecteur = "INSERT INTO voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id, secteur_id) VALUES (:nom, :hauteur, :nombre_points, :nombre_longueurs, :cotation_id, :secteur_id); COMMIT";
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			mSPS.addValue("nom", voie.getNom());
			mSPS.addValue("hauteur", voie.getHauteur());
			mSPS.addValue("nombre_points", voie.getNombrePoints());
			mSPS.addValue("nombre_longueurs", voie.getNombreLongueur());
			mSPS.addValue("cotation_id", getDaoHandler().getCotationDao().getCotationId(voie.getCotation()));
			
			if(hasSecteur) {
				mSPS.addValue("secteur_id", id);
				int result = nPJT.update(sQLVoieSecteur, mSPS);
				return result;
				
			}else {
				mSPS.addValue("site_id", id);
				int result = nPJT.update(sQLVoieSite, mSPS);
				return result;
			}
		}
		return 0; 
	}

}
