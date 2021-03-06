package com.basic.integrate.dao;


import com.basic.integrate.dto.Organization;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    int insert(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    int insertSelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    Organization selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Organization record);

    List<Organization> query(Organization organization);

    void batchOperate(String[] ids);
}