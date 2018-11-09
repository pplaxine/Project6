package com.philippe75.p6.model.bean.site;

public enum Dept {
	AIN("01 Ain"),
	AISNE("02 Aisne"),
	ALLIER("03 Allier"),
	ALPES_DE_HAUTE_PROVENCE("04 Alpes-de-Haute-Provence"),
	HAUTES_ALPES("05 Hautes-Alpes"),
	ALPES_MARITIMES("06 Alpes-Maritimes"),
	ARDECHE("07 Ardèche"),
	ARDENNES("08 Ardennes"),
	ARIEGE("09 Ariège"),
	AUBE("10 Aube"),
	AUDE("11 Aude"),
	AVEYRON("12 Aveyron"),
	BOUCHE_DU_RHONE("13 Bouches-du-Rhône"),
	CALVADOS("14 Calvados"),
	CANTAL("15 Cantal"),
	CHARENTE("16 Charente"),
	CHARENTE_MARITIME("17 Charente-Maritime"),
	CHER("18 Cher"),
	CORREZE("19 Corrèze"),
	CORSE_DU_SUD("2A Cose-du-Sud"),
	HAUTE_CORSE("2B Haute-Corse"),
	COTE_D_OR("21 Côte-d'Or"),
	COTE_D_ARMOR("22 Côtes-d'Armor"),
	CREUSE("23 Creuse"),
	DORDOGNE("24 Dordogne"),
	DOUBS("25 Doubs"),
	DROME("26 Drôme"),
	EURE("27 Eure"),
	EURE_ET_LOIR("28 Eures-et-Loir"),
	FINISTERE("29 Finistère"),
	GARD("30 Gard"),
	HAUTE_GARONNE("31 Haute-Garonne"),
	GERS("32 Gers"),
	GIRONDE("33 Gironde"),
	HERAULT("34 Hérault"),
	ILLE_ET_VILAINE("35 Ille-et-Vilaine"),
	INDRE("36 Indre"),
	INDRE_ET_LOIRE("37 Indre-et-Loire"),
	ISERE("38 Isère");
	
	
	private String dept="";
	
	Dept(String dept){
		this.dept = dept;
	}

	@Override
	public String toString() {
		return dept;
	}
	
	
}
