package com.philippe75.p6.business.contract.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.philippe75.p6.model.bean.topo.Topo;

public interface TopoManager {
	
	Topo creerNouveauTopo(HttpServletRequest request);
	
	int saveTopo(Topo topo);
	
	List<Topo> listAllTopo();
	
	Map<String, String> getErreurs();

	String getResult();
}
