package com.philippe75.p6.consumer.impl.dao;

import javax.inject.Named;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.philippe75.p6.consumer.contract.dao.DeptDao;
import com.philippe75.p6.consumer.impl.rowmapper.DeptRM;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;

@Named("deptDao")
public class DeptDaoImpl extends AbstractDaoImpl implements DeptDao{

	@Override
	public Dept findDept(int site_id) {
String sQL = "SELECT * FROM public.dept WHERE dept.id IN (SELECT site.dept_id FROM site WHERE site.id =:site_id)";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();

		if(Integer.toString(site_id) != null) {
			mSPS.addValue("site_id", site_id);
		}
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		RowMapper<Dept> rm = new DeptRM();
		
		return nPJT.queryForObject(sQL, mSPS, rm);
	}
	
	@Override
	public int getDeptId(Dept dept) {
		
		String sQL = "SELECT dept.id FROM dept WHERE nom = :nom";
				
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("nom", dept.name() );
		
		int dept_id = nPJT.queryForObject(sQL,mSPS, Integer.class);

		return dept_id;
		
	}

}
