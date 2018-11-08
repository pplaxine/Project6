package com.philippe75.p6.consumer.impl.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.CotationDao;
import com.philippe75.p6.consumer.impl.rowmapper.CotationRM;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Site;

public class CotationDaoImpl extends AbstractDaoImpl implements CotationDao{

	@Override
	public Cotation findCotation(int id_cotation) {
	
		String sQL = "SELECT * FROM public.cotation WHERE id=:id";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();

		if(Integer.toString(id_cotation) != null) {
			mSPS.addValue("id", id_cotation);
		}
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		RowMapper<Cotation> rm = new CotationRM();
		
		return nPJT.queryForObject(sQL, mSPS, rm);
	}

}
