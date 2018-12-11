package com.philippe75.p6.consumer.impl.dao;

import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.philippe75.p6.consumer.contract.dao.TopoDao;
import com.philippe75.p6.consumer.impl.rowmapper.LocationTopoRM;
import com.philippe75.p6.consumer.impl.rowmapper.LocationTopoRMWithTopoName;
import com.philippe75.p6.consumer.impl.rowmapper.TopoRM;
import com.philippe75.p6.model.bean.topo.LocationTopo;
import com.philippe75.p6.model.bean.topo.Topo;
import com.philippe75.p6.model.bean.utilisateur.CompteUtilisateur;

@Named("topoDao")
public class TopoDaoImpl extends AbstractDaoImpl implements TopoDao{
	
	
	//---------------------- TOPO ---------------------------------
	@Override
	public int findTopoId(String nom_topo) {

		String sQL = "SELECT topo.id FROM public.topo WHERE nom = :nom";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("nom", nom_topo );
		try {
			return nPJT.queryForObject(sQL,mSPS, Integer.class);
			
		} catch (DataAccessException e) {	
			e.printStackTrace();
			return 0;
		} 
	}
	
	@Override
	public int findNumberOfReservations(int topo_id) {
		String sQL = "SELECT COUNT(*) FROM public.location_topo WHERE topo_id = :topo_id AND accepte = true";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("topo_id", topo_id );
		
		try {
			return nPJT.queryForObject(sQL,mSPS, Integer.class);

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return 0;
		} 
	}
	
	@Override
	public List<Topo> listAllTopo() {
		
		String sQL = 		"SELECT compte_utilisateur.pseudo, topo.* FROM topo "
						+ 	"LEFT JOIN compte_utilisateur ON compte_utilisateur.id = topo.createur_id";		
		
		JdbcTemplate jT = new JdbcTemplate(getDataSource());
		
		RowMapper<Topo> rm = new TopoRM();		
		
		List<Topo> listTopo = (List<Topo>)jT.query(sQL, rm);
		for (Topo topo : listTopo) {
			if(getDaoHandler().getSiteDao().findSiteWithTopoId(topo.getId()) != null) {
				topo.setSite(getDaoHandler().getSiteDao().findSiteWithTopoId(topo.getId()));
			}
			if(findNumberOfReservations(topo.getId()) != 0) {
				topo.setNbreResa(findNumberOfReservations(topo.getId()));
			}
			
		}
		return listTopo;
	}
	
	@Override
	public List<Topo> listAllTopoForUser() {
		
		CompteUtilisateur cu = getUser();
		int user_id = cu.getId();
		
		String sQL = 		"SELECT compte_utilisateur.pseudo, topo.* FROM topo "
						+ 	"LEFT JOIN compte_utilisateur ON compte_utilisateur.id = topo.createur_id WHERE createur_id = :user_id";		
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		mSPS.addValue("user_id", user_id);		
		RowMapper<Topo> rm = new TopoRM();
		
		try {
			List<Topo> listTopo = (List<Topo>)nPJT.query(sQL, mSPS,rm);
			if(listTopo != null) {
				for (Topo topo : listTopo) {
					if(findLocationTopo(topo.getId()) != null) {
						List<LocationTopo> listLocationTopo = findLocationTopo(topo.getId());
						Collections.sort(listLocationTopo);
						topo.setListLocationTopo(listLocationTopo);
					}
				}
			}
			return listTopo;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return null;
		} 
	}


	@Override
	public int createTopo(Topo topo) {
		
		String sQL = "INSERT INTO public.topo (nom, presentation, createur_id) VALUES (:nom, :presentation, :createur_id); COMMIT";
		if(topo != null) {
			
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userPseudo = auth.getName();
			
			try {
				CompteUtilisateur cu = getDaoHandler().getCompteUtilisateurDao().findCompteUtilisateur(userPseudo);
				if (cu != null) {
					MapSqlParameterSource mSPS = new MapSqlParameterSource();
					
					mSPS.addValue("nom", topo.getNom());
					mSPS.addValue("presentation", topo.getPresentation());
					mSPS.addValue("createur_id", cu.getId());
					
					NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
					int result = nPJT.update(sQL, mSPS);
					if(result > 0) {
						int resultSite = getDaoHandler().getSiteDao().saveSite(topo.getSite(), findTopoId(topo.getNom()));
						return resultSite;
					}
				}
			} catch (DataAccessException e) {	
				e.printStackTrace();
				return 0;
			} 
		}
		return 0;
	}

	@Override
	public Topo findTopo(int topo_id) {
		String sQL = "SELECT compte_utilisateur.pseudo, topo.* FROM topo "
					+ "JOIN compte_utilisateur ON compte_utilisateur.id = topo.createur_id "
					+ "WHERE topo.id = :topo_id";		
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		mSPS.addValue("topo_id", topo_id);
		
		RowMapper<Topo> rm = new TopoRM();
		
		try {
			Topo topo = (Topo)nPJT.queryForObject(sQL, mSPS ,rm);
			if(topo != null && getDaoHandler().getSiteDao().findSiteWithTopoId(topo.getId()) != null) {
				topo.setSite(getDaoHandler().getSiteDao().findSiteWithTopoId(topo.getId()));
			}
			if(topo != null && getDaoHandler().getTopoDao().findLocationTopo(topo.getId()) != null) {
				topo.setListLocationTopo(getDaoHandler().getTopoDao().findLocationTopo(topo.getId()));
			}
			
			return topo;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return null;
		} 
	}

	//---------------------- LOCATION TOPO ---------------------------------
	
	@Override
	public List<LocationTopo> findLocationTopo(int topo_id) {
		String sQL = "SELECT compte_utilisateur.pseudo, location_topo.* FROM location_topo "
					+ "JOIN compte_utilisateur ON compte_utilisateur.id = location_topo.emprunteur_id "
					+ "WHERE topo_id = :topo_id";		
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		
		mSPS.addValue("topo_id", topo_id);
		
		RowMapper<LocationTopo> rm = new LocationTopoRM();
		
		try {
			List<LocationTopo> listLocationTopo = (List<LocationTopo>)nPJT.query(sQL, mSPS ,rm);
			return	listLocationTopo;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return null;
		} 
	}
	
	@Override
	public List<LocationTopo> findAllLocationOfUser() {
		String sQL = "SELECT compte_utilisateur.pseudo, topo.nom , location_topo.* FROM location_topo "
				+ "JOIN compte_utilisateur ON compte_utilisateur.id = location_topo.emprunteur_id "
				+ "JOIN topo ON topo.id = location_topo.topo_id "
				+ "WHERE emprunteur_id = :user_id";		
		CompteUtilisateur cu = getUser();
		
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		
		mSPS.addValue("user_id", cu.getId());
		
		RowMapper<LocationTopo> rm = new LocationTopoRMWithTopoName();
		
		try {
			List<LocationTopo> listLocationTopo = (List<LocationTopo>)nPJT.query(sQL, mSPS ,rm);
			return	listLocationTopo;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return null;
		} 
	}
	
	@Override
	public int saveDemandeLocationTopo(LocationTopo locationTopo) {
		
		String sQL = "INSERT INTO location_topo (date_debut_location, date_fin_location, topo_id, emprunteur_id) VALUES (:date_debut_location, :date_fin_location, :topo_id, :emprunteur_id); COMMIT";
		
		// retrouve l'utilisateur 
		CompteUtilisateur cu = getUser();
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		
		mSPS.addValue("date_debut_location", locationTopo.getDateDebutLocation());
		mSPS.addValue("date_fin_location", locationTopo.getDateFinLocation());
		mSPS.addValue("topo_id", locationTopo.getTopo_id());
		mSPS.addValue("emprunteur_id", cu.getId());
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		try {
			int result = nPJT.update(sQL, mSPS);
			return result;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return 0;
		} 
		
	}

	@Override
	public int repondreDemandeLocation(int location_id, Boolean accepter) {
		
		String sQL = "UPDATE location_topo SET accepte = :accepter WHERE id= :location_id; COMMIT";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		mSPS.addValue("accepter", accepter);
		mSPS.addValue("location_id", location_id);
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		try {
			int result = nPJT.update(sQL, mSPS);
			return result;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return 0;
		} 
	}

	@Override
	public int deleteLocation(int location_id) {
		String sQL = "DELETE FROM location_topo WHERE id= :location_id; COMMIT";
		
		MapSqlParameterSource mSPS = new MapSqlParameterSource();
		mSPS.addValue("location_id", location_id);
		
		NamedParameterJdbcTemplate nPJT = new NamedParameterJdbcTemplate(getDataSource());
		
		try {
			int result = nPJT.update(sQL, mSPS);
			return result;

		} catch (DataAccessException e) {	
			e.printStackTrace();
			return 0;
		} 
	}
}
