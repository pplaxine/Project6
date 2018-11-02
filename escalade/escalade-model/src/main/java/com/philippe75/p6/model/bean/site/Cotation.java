package com.philippe75.p6.model.bean.site;

public enum Cotation {
	COTATION_1("1"),
	COTATION_2("2"),
	COTATION_3("3"),
	COTATION_4("4"),
	COTATION_5("5"),
	COTATION_5A("5a"),
	COTATION_5B("5b"),
	COTATION_5C("5c"),
	COTATION_6A("6a"),
	COTATION_6A_PLUS("6a+"),
	COTATION_6B("6b"),
	COTATION_6B_PLUS("6b+"),
	COTATION_6C("6c"),
	COTATION_6C_PLUS("6c+"),
	COTATION_7A("7a"),
	COTATION_7A_PLUS("7a+"),
	COTATION_7B("7b"),
	COTATION_7B_PLUS("7b+"),
	COTATION_7C("7c"),
	COTATION_7C_PLUS("7c+"),
	COTATION_8A("8a"),
	COTATION_8A_PLUS("8a+"),
	COTATION_8B("8b"),
	COTATION_8B_PLUS("8b+"),
	COTATION_8C("8c"),
	COTATION_8C_PLUS("8c+"),
	COTATION_9A("9a"),
	COTATION_9A_PLUS("9a+"),
	COTATION_9B("9b"),
	COTATION_9B_PLUS("9b+"),
	COTATION_9C("9c");
	
	private String cotation = "";
	
	Cotation(String cotation){
		this.cotation=cotation;
	}

	@Override
	public String toString() {
		return cotation;
	}

	
	
	
	
}
