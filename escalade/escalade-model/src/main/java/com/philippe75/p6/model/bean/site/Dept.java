package com.philippe75.p6.model.bean.site;

import java.util.HashMap;
import java.util.Map;

public enum Dept {
	TOUS("- Tous -"),
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
	ISERE("38 Isère"),
	JURA("39 Jura"),
	LANDES("40 Landes"),
	LOIR_ET_CHER("41 Loir-et-cher"),
	LOIRE("42 Loire"),
	HAUTE_LOIRE("43 Haute Loire"),
	LOIRE_ATLANTIQUE("44 Loire Atlantique"),
	LOIRET("45 Loiret"),
	LOT("46 Lot"),
	LOT_ET_GARONNE("47 Lot-et-Garonne"),
	LOZERE("48 Lozère"),
	MAINE_ET_LOIRE("49 Maine_et_Loire"),
	MANCHE("50 Manche"),
	MARNE("51 Marne"),
	HAUTE_MARNE("52 Haute Marne"),
	MAYENNE("53 Mayenne"),
	MEURTHE_ET_MOSELLE("54 Meurthe-et-moselle"),
	MEUSE("55 Meuse"),
	MORBIHAN("56 Morbihan"),
	MOSELLE("57 Moselle"),
	NIEVRE("58 Nièvre"),
	NORD("59 Nord"),
	OISE("60 Oise"),
	ORNE("61 Orne"),
	PAS_DE_CALAIS("62 Pas-de-Calais"),
	PUY_DE_DOME("63 Puy-de-Dôme"),
	PYRENEES_ATLANTIQUE("64 Pyrénées Atlantiques"),
	HAUTES_PYRENEES("65 Hautes Pyrénées"),
	PYRENEES_ORIENTALES("66 Pyrénées Orientales"),
	BAS_RHIN("67 Bas-Rhin"),
	HAUT_RHIN("68 Haut-Rhin"),
	RHONE("69 Rhône"),
	HAUTE_SAONE("70 Haute Saône"),
	SAONE_ET_LOIRE("71 Saône-et-Loire"),
	SARTHE("72 Sarthe"),
	SAVOIE("73 Savoie"),
	HAUTE_SAVOIE("74 Haute Savoie"),
	PARIS("75 Paris"),
	SEINE_MARITIME("76 Seine Maritime"),
	SEINE_ET_MARNE("77 Seine-et-Marne"),
	YVELINES("78 Yvelines"),
	DEUX_SEVRES("79 Deux-Sèvres"),
	SOMME("80 Somme"),
	TARN("81 Tarn"),
	TARN_ET_GARONNE("82 Tarn-et-Garonne"),
	VAR("83 Var"),
	VAUCLUSE("84 Vaucluse"),
	VENDEE("85 Vendée"),
	VIENNE("86 Vienne"),
	HAUTE_VIENNE("87 Haute Vienne"),
	VOSGES("88 Vosges"),
	YONNE("89 Yonne"),
	TERRITOIRE_DE_BELFORT("90 Territoire de Belfort"),
	ESSONNE("91 Essonne"),
	HAUTS_DE_SEINE("92 Hauts-de-Seine"),
	SEINES_ST_DENIS("93 Seine-St-Denis"),
	VAL_DE_MARNE("94 Val-de-Marne"),
	VAL_D_OISE("95 Val-D'Oise");
	
	
	private String dept="";
	
	Dept(String dept){
		this.dept = dept;
	}

	@Override
	public String toString() {
		return dept;
	}
	
    private static final Map<String, Dept> lookup2 = new HashMap<>();
    
    static
    {
        for(Dept dept2 : Dept.values())
        {
            lookup2.put(dept2.toString(), dept2);
        }
    }
  
    
 
 

	public static Dept get(String dept2)
    {
        return lookup2.get(dept2);
    }
	
	
}
