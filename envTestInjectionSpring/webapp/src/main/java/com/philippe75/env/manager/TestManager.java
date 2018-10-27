package com.philippe75.env.manager;

import javax.inject.Named;

@Named
public class TestManager {
	
	public String getString() {
		String str = "Victoire !!!! Abdoulail l'hérisson.";
		return str;
	}

}
