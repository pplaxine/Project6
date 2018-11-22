package com.philippe75.p6.consumer.impl.dao;

import java.util.List;

import javax.inject.Named;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.consumer.contract.dao.TopoDao;
import com.philippe75.p6.consumer.impl.rowmapper.SiteRM;
import com.philippe75.p6.consumer.impl.rowmapper.TopoRM;
import com.philippe75.p6.model.bean.site.Site;
import com.philippe75.p6.model.bean.topo.Topo;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("topoDao")
public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao{
	
	@Override
	public int findTopoId(String nom_topo) {

		String sQL = "SELECT topo.id FROM public.topo WHERE nom = :nom";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("nom", nom_topo );
		
		return nPJT.queryForObject(sQL,mSPS, Integer.class);
	
	}	
	
	@Override
	public List<Topo> listAllTopo() {
		
		String sQL = "SELECT * FROM public.topo";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Topo> rm = new TopoRM();		
		
		List<Topo> listTopo = (List<Topo>)jT.query(sQL, rm);
		for (Topo topo : listTopo) {
			if(getDaoHandler().getSiteDao().findSiteWithTopoId(topo.getId()) != null) {
				topo.setSite(getDaoHandler().getSiteDao().findSiteWithTopoId(topo.getId()));
				System.out.println("Emprunteur " + topo.getEmprunteur_id());
				System.out.println("Preteur " + topo.getPreteur_id());
			}
			
			
//			if( topo.getPreteur_id() != 0 && getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(topo.getPreteur_id()) != null ) {
//				CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(topo.getPreteur_id());
//				topo.setPreteur(cu.getPseudo());
//			}
//			if(topo.getEmprunteur_id() != 0 && getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(topo.getEmprunteur_id()) != null) {
//				CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(topo.getEmprunteur_id());
//				topo.setEmprunteur(cu.getPseudo());
//			}
			
		}
		return listTopo;
	}

	@Override
	public int createTopo(Topo topo) {
		
		String sQL = "INSERT INTO public.topo (nom, presentation, disponible, preteur_id) VALUES (:nom, :presentation, :disponible, :preteur_id); COMMIT";
		if(topo != null) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userPseudo = auth.getName();
			CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(userPseudo);
			if (cu != null) {
				MapSqlParameterSource mSPS = new MapSqlParameterSource();
				
				mSPS.addValue("nom", topo.getNom());
				mSPS.addValue("presentation", topo.getPresentation());
				topo.setDisponible(true);
				mSPS.addValue("disponible", topo.isDisponible());	
				mSPS.addValue("preteur_id", cu.getId());
			
				NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
				int result = nPJT.update(sQL, mSPS);
				if(result > 0) {
					int resultSite = getDaoHandler().getSiteDao().saveSite(topo.getSite(), findTopoId(topo.getNom()));
					return resultSite;
				}
			}
		}
		return 0;
	}



}
