package com.basic.integrate.service.impl;


import com.basic.integrate.dao.OrganizationMapper;
import com.basic.integrate.dto.Organization;
import com.basic.integrate.service.OrganizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Resource
    private OrganizationMapper organizationMapper;

    @Override
    public int insert(Organization organization) {
        return organizationMapper.insertSelective(organization);
    }

    @Override
    public int update(Organization organization) {
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    @Override
    public List<Organization> list(Organization organization) {
        return organizationMapper.query(organization);
    }

    @Override
    public void batchOperate(String ids) {
        String[] array = ids.split(",");
        organizationMapper.batchOperate(array);
    }
}
