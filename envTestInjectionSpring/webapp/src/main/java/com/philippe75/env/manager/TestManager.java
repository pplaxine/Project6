package com.philippe75.env.manager;


import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.env.beans.TestBean;

	 

@Named
public class TestManager {
	
	@Inject
	TestBean testBean;
	
	
	
	public void setTestBean(TestBean testBean) {
		this.testBean = testBean;
	}



	public String getString() {
		String str = "Victoire !!!! Abdoulail l'hérisson et " + testBean.getPrenom() + ", nombre de tuples dans bdd = " + testBean.getNumberOfTest();
		return str;
	}

}
