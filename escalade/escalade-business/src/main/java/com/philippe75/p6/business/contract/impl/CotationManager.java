package com.philippe75.p6.business.contract.impl;

import java.util.List;
import com.philippe75.p6.model.bean.site.Cotation;

public interface CotationManager {
	
	List<Cotation> listAllCotation();
	
	Cotation findCotation(int voie_id);
	int getCotationId(Cotation cotation);
}
