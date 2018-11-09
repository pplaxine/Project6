package com.philippe75.p6.consumer.contract.dao;

import com.philippe75.p6.model.bean.site.Cotation;

public interface CotationDao {

	Cotation findCotation(int voie_id);
}
