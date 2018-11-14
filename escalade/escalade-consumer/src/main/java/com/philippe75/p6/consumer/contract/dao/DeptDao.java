package com.philippe75.p6.consumer.contract.dao;

import java.util.List;

import com.philippe75.p6.model.bean.site.Dept;

public interface DeptDao {
	
	Dept findDept(int site_id);
	
	int getDeptId(Dept dept);
}
