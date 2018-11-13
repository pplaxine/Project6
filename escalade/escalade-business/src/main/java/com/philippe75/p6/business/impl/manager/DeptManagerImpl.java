package com.philippe75.p6.business.impl.manager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.philippe75.p6.business.contract.impl.DeptManager;
import com.philippe75.p6.business.contract.impl.SiteManager;
import com.philippe75.p6.model.bean.site.Dept;
import com.philippe75.p6.model.bean.site.Site;

@Named("deptManager")
public class DeptManagerImpl extends AbstractManager implements DeptManager{

	@Override
	public List<Dept> listAllDepts() {
		List<Dept> listDept = new ArrayList<>();
		Dept [] depts = Dept.values();
		for (Dept dept : depts) {
			listDept.add(dept);
		}
		return listDept;
	}

	
}
