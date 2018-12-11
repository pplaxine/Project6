package com.philippe75.p6.consumer.contract.dao;

import java.util.List;
import com.philippe75.p6.model.bean.topo.LocationTopo;
import com.philippe75.p6.model.bean.topo.Topo;

public interface TopoDao {

	int findTopoId(String nom_topo);
	
	int findNumberOfReservations(int topo_id); 
	
	Topo findTopo(int topo_id);
	
	int createTopo(Topo topo);
	
	int repondreDemandeLocation(int location_id, Boolean accepter);
	
	List<Topo> listAllTopo();
	
	List<Topo> listAllTopoForUser();
	
	List<LocationTopo> findLocationTopo(int topo_id);
	
	List<LocationTopo> findAllLocationOfUser();
	
	int deleteLocation(int location_id);
	
	int saveDemandeLocationTopo(LocationTopo locationTopo);
	
}
