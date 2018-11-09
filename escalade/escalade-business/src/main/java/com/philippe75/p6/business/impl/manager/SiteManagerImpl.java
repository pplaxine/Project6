package com.philippe75.p6.business.impl.manager;

import java.util.List;

import javax.inject.Named;

import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.model.bean.site.Site;

@Named("siteManager")
public class SiteManagerImpl extends AbstractManager implements SiteManager{

	@Override
	public List<Site> listAllSite() {
		return getDaoHandler().getSiteDao().listAllSite();
	}

	@Override
	public Site findSite(int id) {
		return getDaoHandler().getSiteDao().findSite(id);
	}

	
}
