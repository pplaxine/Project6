package com.philippe75.p6.consumer.contract.dao;

import com.philippe75.p6.model.bean.site.Dept;

public interface DeptDao {
	
	Dept findDept(int site_id);
}
