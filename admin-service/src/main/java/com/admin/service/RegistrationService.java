package com.admin.service;

import java.util.List;

import com.admin.bean.LoginBean;
import com.admin.bean.RegistrationBean;
import com.admin.entity.RegistrationForm;

public interface RegistrationService {
      RegistrationBean save(RegistrationBean registration);
      RegistrationBean getById(int id);
      List<RegistrationBean> getAll();
      void delete(int id);
      void update(RegistrationBean registration);
      //boolean getDetails(String email);
	RegistrationForm validateLogin(LoginBean loginBean);
}
