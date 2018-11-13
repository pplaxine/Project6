package com.philippe75.p6.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.p6.business.contract.impl.CotationManager;
import com.philippe75.p6.business.contract.impl.DeptManager;
import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.model.bean.site.Cotation;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;

@Named("cotationManager")
public class CotationManagerImpl extends AbstractManager implements CotationManager {

	@Override
	public List<Cotation> listAllCotation() {
		List<Cotation> listCotation = new ArrayList<>();
		Cotation [] cotations = Cotation.values();
		for (Cotation cotation : cotations) {
			listCotation.add(cotation);
		}
		return listCotation;
	}

	
}
