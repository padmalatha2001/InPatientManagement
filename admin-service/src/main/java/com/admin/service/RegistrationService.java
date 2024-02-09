package com.admin.service;

import java.util.List;

import com.admin.bean.RegistrationBean;

public interface RegistrationService {
      RegistrationBean save(RegistrationBean registration);
      RegistrationBean getById(int id);
      List<RegistrationBean> getAll();
      void delete(int id);
      void update(RegistrationBean registration);
}
