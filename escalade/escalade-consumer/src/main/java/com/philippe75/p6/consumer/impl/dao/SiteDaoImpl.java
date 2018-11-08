package com.philippe75.p6.consumer.impl.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.SiteDao;
import com.philippe75.p6.consumer.impl.rowmapper.CompteUtilisateurRM;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

public class SiteDaoImpl extends AbstractDaoImpl implements SiteDao{
	
	@Override
	public Site findSite(String nom) {
		
			String sQL = "SELECT * FROM public.compte_utilisateur WHERE id=:id";
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();

			if(nom != null) {
				mSPS.addValue("nom", nom);
			}
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			
			RowMapper<Site> rm = new SiteRM();
			
			return nPJT.queryForObject(sQL, mSPS, rm);
	}

}
