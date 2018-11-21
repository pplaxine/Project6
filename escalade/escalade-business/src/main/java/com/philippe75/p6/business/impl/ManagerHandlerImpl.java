package com.philippe75.p6.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.business.contract.ManagerHandler;
import com.philippe75.p6.business.contract.impl.CommentaireManager;
import com.philippe75.p6.business.contract.impl.CompteUtilisateurManager;
import com.philippe75.p6.business.contract.impl.CotationManager;
import com.philippe75.p6.business.contract.impl.DeptManager;
import com.philippe75.p6.business.contract.impl.SecteurManager;
import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.business.contract.impl.TopoManager;
import com.philippe75.p6.business.contract.impl.VoieManager;

@Named("managerHandler")
public class ManagerHandlerImpl implements ManagerHandler {
	
	@Inject
	private CompteUtilisateurManager compteUtilisateurManager;
	
	@Inject
	private SiteManager siteManager;
	
	@Inject
	private DeptManager deptManager;

	@Inject
	private CotationManager cotationManager;
	
	@Inject
	private SecteurManager secteurManager;
	
	@Inject
	private VoieManager voieManager;
	
	@Inject
	private CommentaireManager commentaireManager;
	
	@Inject
	private TopoManager topoManager;
	
	
	@Override
	public CompteUtilisateurManager getCompteUtilisateurManager() {
		return compteUtilisateurManager;
	}


	public SiteManager getSiteManager() {
		return siteManager;
	}


	@Override
	public DeptManager getDeptManager() {
		return deptManager;
	}


	@Override
	public CotationManager getCotationManager() {
		return cotationManager;
	}

	@Override
	public SecteurManager getSecteurManager() {
		return secteurManager;
	}

	@Override
	public VoieManager getVoieManager() {
		return voieManager;
	}


	public CommentaireManager getCommentaireManager() {
		return commentaireManager;
	}


	public TopoManager getTopoManager() {
		return topoManager;
	}


	public void setTopoManager(TopoManager topoManager) {
		this.topoManager = topoManager;
	}

	
	
	
	
	
	
	

}
