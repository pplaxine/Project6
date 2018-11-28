package com.philippe75.p6.business.contract.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.topo.LocationTopo;
import com.philippe75.p6.model.bean.topo.Topo;

public interface TopoManager {
	
	Topo creerNouveauTopo(HttpServletRequest request);
	
	int saveTopo(Topo topo);
	
	LocationTopo creerNouvelleDemandeLocation(HttpServletRequest request);
	
	Topo findTopo(int topo_id);
	
	List<Topo> listAllTopo();
	
	List<Topo> listAllTopoForUser();
	
	Topo getLastTopoAdded();
	
	Map<String, String> getErreurs();

	String getResult();
	
	int saveDemandeLocationTopo(LocationTopo locationTopo);
	
	int repondreDemandeLocation(int location_id, Boolean accepter);
	
	int deleteLocation(int location_id);
	
	List<LocationTopo> findAllLocationOfUser();
}
