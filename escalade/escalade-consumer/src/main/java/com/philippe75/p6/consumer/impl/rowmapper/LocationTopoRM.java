package com.philippe75.p6.consumer.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

import com.philippe75.p6.model.bean.topo.LocationTopo;

public class LocationTopoRM implements RowMapper<LocationTopo> {
	
	@Override	
	public LocationTopo mapRow(ResultSet rs, int rn) throws SQLException {		
		
		LocationTopo lt = new LocationTopo();
		
		lt.setId(rs.getInt("id"));
		lt.setTopo_id(rs.getInt("topo_id"));
		lt.setDateDebutLocation(timestampToLocalDateTime(rs.getTimestamp("date_debut_location")));
		lt.setDateFinLocation(timestampToLocalDateTime(rs.getTimestamp("date_fin_location")));
		lt.setDateDebutLocationFormat(FormatLocalDateTime(lt.getDateDebutLocation()));
		lt.setDateFinLocationFormat(FormatLocalDateTime(lt.getDateFinLocation()));
		
		lt.setEmprunteur(rs.getString("pseudo"));
		Boolean isAccepte = rs.getBoolean("accepte");
		lt.setAccepte(rs.wasNull()?null:isAccepte);
		return  lt;		
	}
	
	private LocalDateTime timestampToLocalDateTime(Timestamp date) {
		LocalDateTime ldt =  date.toLocalDateTime();
		return ldt;
	}
	
	private String FormatLocalDateTime(LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy à HH:mm");
		String newFormat = date.format(formatter);
		return newFormat;
	}
}