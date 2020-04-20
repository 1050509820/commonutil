package com.basic.integrate.service;



import com.basic.integrate.dto.Organization;

import java.util.List;

public interface OrganizationService {
    int insert(Organization organization);
    int update(Organization organization);
    List<Organization> list(Organization organization);
    void batchOperate(String ids);


}
