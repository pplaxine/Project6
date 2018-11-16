package com.philippe75.p6.consumer.contract;

import com.philippe75.p6.consumer.contract.dao.CommentaireDao;
import com.philippe75.p6.consumer.contract.dao.CompteUtilisateurDao;
import com.philippe75.p6.consumer.contract.dao.CotationDao;
import com.philippe75.p6.consumer.contract.dao.DeptDao;
import com.philippe75.p6.consumer.contract.dao.SecteurDao;
import com.philippe75.p6.consumer.contract.dao.SiteDao;
import com.philippe75.p6.consumer.contract.dao.VoieDao;

public interface DaoHandler {
	
	CompteUtilisateurDao getCompteUtilisateurDao();
	
	SiteDao getSiteDao();
	
	SecteurDao getSecteurDao();

	VoieDao getVoieDao();
	
	CotationDao getCotationDao();
	
	DeptDao getDeptDao();
	
	CommentaireDao getCommentaireDao();
	
}
