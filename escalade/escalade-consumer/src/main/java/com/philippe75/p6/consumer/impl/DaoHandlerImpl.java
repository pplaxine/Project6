package com.philippe75.p6.consumer.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.philippe75.p6.consumer.contract.DaoHandler;
import com.philippe75.p6.consumer.contract.dao.CommentaireDao;
import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.consumer.contract.dao.CotationDao;
import com.philippe75.p6.consumer.contract.dao.DeptDao;
import com.philippe75.p6.consumer.contract.dao.SecteurDao;
import com.philippe75.p6.consumer.contract.dao.SiteDao;
import com.philippe75.p6.consumer.contract.dao.TopoDao;
import com.philippe75.p6.consumer.contract.dao.VoieDao;

@Named("daoHandler")
public class DaoHandlerImpl implements DaoHandler{
	
	@Inject
	private CompteUtilisateurDao compteUtilisateurDao;

	@Inject
	private SiteDao siteDao;
	
	@Inject
	private SecteurDao secteurDao;
	
	@Inject
	private VoieDao voieDao;
	
	@Inject
	private CotationDao cotationDao;
	
	@Inject
	private DeptDao deptDao;
	
	@Inject
	private CommentaireDao commentaireDao;
	
	@Inject 
	private TopoDao topoDao;

	
	@Override
	public CompteUtilisateurDao getCompteUtilisateurDao() {
		return compteUtilisateurDao;
	}

	@Override
	public SiteDao getSiteDao() {
		return siteDao;
	}
	
	@Override
	public SecteurDao getSecteurDao() {
		return secteurDao;
	}
	
	@Override
	public VoieDao getVoieDao() {
		return voieDao;
	}

	@Override
	public CotationDao getCotationDao() {
		return cotationDao;
	}

	@Override
	public DeptDao getDeptDao() {
		return deptDao;
	}

	public CommentaireDao getCommentaireDao() {
		return commentaireDao;
	}

	public TopoDao getTopoDao() {
		return topoDao;
	}
		
}
