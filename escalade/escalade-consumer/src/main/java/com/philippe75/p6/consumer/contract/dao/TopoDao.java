package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.topo.Topo;

public interface TopoDao {

	int findTopoId(String nom_topo);
	
	int createTopo(Topo topo);
	
	List<Topo> listAllTopo();
	
}
