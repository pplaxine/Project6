package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.philippe75.p6.consumer.contract.dao.SiteDao;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.model.bean.site.Site;

@Named("siteDao")
public class SiteDaoImpl extends AbstractDaoImpl implements SiteDao{

	@Override
	public Site findSite(int site_id) {
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
				
		String sQL = "SELECT * FROM public.site WHERE id = ?";
		
		RowMapper<Site> rm = new SiteRM();
		
		Site site = (Site)jT.queryForObject(sQL, new Object[] {site_id}, rm);
		
		site.setSecteurs(getDaoHandler().getSecteurDao().listSecteur(site_id));
		site.setDept(getDaoHandler().getDeptDao().findDept(site.getId()));
		
		return site;
	}

	@Override
	public List<Site> listAllSite() {
		
		String sQL = "SELECT * FROM public.site";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Site> rm = new SiteRM();		
		
		List<Site> listSite = (List<Site>)jT.query(sQL, rm);
		
		for (Site site : listSite) {
			site.setSecteurs(getDaoHandler().getSecteurDao().listSecteur(site.getId()));
			site.setDept(getDaoHandler().getDeptDao().findDept(site.getId()));
		}
		
		return listSite;
		
	}
	
	
	
}
