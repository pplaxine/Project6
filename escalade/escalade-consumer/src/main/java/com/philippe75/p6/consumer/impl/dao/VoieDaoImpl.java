package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		
		return (Voie)jT.queryForObject(sQL, new Object[] {id}, rm);
	}

	@Override
	public List<Voie> listVoie(int secteur_id) {
		
		String sQL = "SELECT * FROM public.voie WHERE secteur_id=?";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Voie> rm = new VoieRM();		
		
		List<Voie> listVoie = (List<Voie>)jT.query(sQL, new Object[] {secteur_id}, rm);	
		return listVoie;
	
	}

}
