package com.philippe75.p6.consumer.impl.dao;

import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.CotationDao;
import com.philippe75.p6.consumer.impl.rowmapper.CotationRM;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Site;

@Named("cotationDao")
public class CotationDaoImpl extends AbstractDaoImpl implements CotationDao{

	@Override
	public Cotation findCotation(int voie_id) {
	
		String sQL = "SELECT * FROM public.cotation WHERE cotation.id IN (SELECT voie.cotation_id FROM voie WHERE voie.id =:voie_id)";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();

		if(Integer.toString(voie_id) != null) {
			mSPS.addValue("voie_id", voie_id);
		}
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		RowMapper<Cotation> rm = new CotationRM();
		
		return nPJT.queryForObject(sQL, mSPS, rm);
	}

}
