package com.philippe75.p6.consumer.impl.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.philippe75.p6.consumer.contract.dao.SiteDao;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.model.bean.site.Secteur;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.site.Voie;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

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
		site.setVoies(getDaoHandler().getVoieDao().listVoie(site.getId(), false));
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
			site.setVoies(getDaoHandler().getVoieDao().listVoie(site.getId(), false)); // lister les voies avec site id 
		}
		return listSite;
	}
	
	@Override
	public List<Site> listAllSiteWithCriteria(String dept, int nombreSecteur, int nombreVoie, boolean topoDisponible) {
		
		String sQL = "SELECT * FROM public.site";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Site> rm = new SiteRM();		
		
		List<Site> listSite = (List<Site>)jT.query(sQL, rm);
		
		for (Site site : listSite) {
			site.setSecteurs(getDaoHandler().getSecteurDao().listSecteur(site.getId()));
			site.setDept(getDaoHandler().getDeptDao().findDept(site.getId()));
			site.setVoies(getDaoHandler().getVoieDao().listVoie(site.getId(), false)); // lister les voies avec site id 
		}
		return listSite;
	}
	
	@Override
	public int getSiteId(String nom) {
		
		String sQL = "SELECT site.id FROM public.site WHERE nom = :nom";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("nom", nom );
		
		int site_id = nPJT.queryForObject(sQL,mSPS, Integer.class);
		
		return site_id;
	}

	@Override
	public int saveSite(Site site) {
	
		String sQL = "INSERT INTO site (nom, lieu, description, date_creation, dept_id, compte_utilisateur_id) VALUES (:nom, :lieu, :description, :date_creation, :dept_id, :compte_utilisateur_id); COMMIT";
		if(site != null) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userPseudo = auth.getName();
			CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(userPseudo);
			//---------------
			
			MapSqlParameterSource mSPS = new MapSqlParameterSource();
			
			mSPS.addValue("nom", site.getNom());
			mSPS.addValue("lieu", site.getLieu());
			mSPS.addValue("description", site.getDescription());
			mSPS.addValue("date_creation", new Date());
			mSPS.addValue("dept_id", getDaoHandler().getDeptDao().getDeptId(site.getDept()));
			mSPS.addValue("compte_utilisateur_id", cu.getId());				// trouver l'id de l'utilisateur !!!! 
			
			NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
			int result = nPJT.update(sQL, mSPS);
			
			if(result != 0 && site.getSecteurs() != null) {
				int site_id = getSiteId(site.getNom());
				for (Secteur secteur : site.getSecteurs()) {
					getDaoHandler().getSecteurDao().saveSecteur(secteur, site_id );
				}
					
				return result;
			}
			
			if(result != 0 && site.getVoies() != null) {
				int site_id = getSiteId(site.getNom());
				for (Voie voie : site.getVoies()) {
					getDaoHandler().getVoieDao().saveVoie(voie, site_id, false);
				}
					
				return result;
			}
			
			return 0;
		}
		return 0;
	}




	
	
	
}
