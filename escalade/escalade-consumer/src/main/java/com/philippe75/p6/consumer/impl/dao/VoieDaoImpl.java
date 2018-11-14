package com.philippe75.p6.consumer.impl.dao;

import java.sql.Types;
import java.util.List;

import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.VoieDao;
import com.philippe75.p6.consumer.impl.rowmapper.VoieRM;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Voie;

import aj.org.objectweb.asm.Type;

@Named("voieDao")
public class VoieDaoImpl extends AbstractDaoImpl implements VoieDao {

	@Override
	public Voie findVoie(int id) {
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
				
		String sQL = "SELECT * FROM public.voie WHERE id=?";
		
		RowMapper<Voie> rm = new VoieRM();
		
		Voie voie = (Voie)jT.queryForObject(sQL, new Object[] {id}, rm);
		voie.setCotation(getDaoHandler().getCotationDao().findCotation(id) );
		
		return voie;
	}
	
	

	@Override
	public List<Voie> listVoie(int secteur_id) {
		
		String sQL = "SELECT * FROM public.voie WHERE secteur_id=?";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Voie> rm = new VoieRM();		
		
		List<Voie> listVoie = (List<Voie>)jT.query(sQL, new Object[] {secteur_id}, rm);
		for (Voie voie : listVoie) {
			voie.setCotation(getDaoHandler().getCotationDao().findCotation(voie.getId()));
		}
		
		return listVoie;
	
	}

	@Override
	public int saveVoie(Voie voie, int secteur_id) {
		
		if(voie != null && secteur_id != 0){
			String sQL = "INSERT INTO voie (nom,hauteur,nombre_points,nombre_longueurs,cotation_id, secteur_id) VALUES (:nom, :hauteur, :nombre_points, :nombre_longueurs, :cotation_id, :secteur_id); COMMIT";
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();
			
			mSPS.addValue("nom", voie.getNom());
			mSPS.addValue("hauteur", voie.getHauteur());
			mSPS.addValue("nombre_points", voie.getNombrePoints());
			mSPS.addValue("nombre_longueurs", voie.getNombreLongueur());
			mSPS.addValue("cotation_id", getDaoHandler().getCotationDao().getCotationId(voie.getCotation()));
			mSPS.addValue("secteur_id", secteur_id);
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			int result = nPJT.update(sQL, mSPS);
			return result;
		}
		return 0; 
	}

}
