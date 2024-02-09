package com.admin.service;

import java.util.List;

import com.admin.bean.DepartmentBean;

public interface DepartmentService {
	DepartmentBean save(DepartmentBean Department);
	DepartmentBean getById(long id);
    List<DepartmentBean> getAll();
    void delete(long id);
}
