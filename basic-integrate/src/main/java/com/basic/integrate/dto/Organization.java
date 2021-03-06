package com.basic.integrate.dto;

import java.io.Serializable;
import java.util.Date;

public class Organization implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.organization_name
     *
     * @mbggenerated
     */
    private String organizationName;//组织名称

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.appid
     *
     * @mbggenerated
     */
    private String appid;//

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.is_enable
     *
     * @mbggenerated
     */
    private String isEnable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.organization_id
     *
     * @mbggenerated
     */
    private String organizationId; //组织编号

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.address
     *
     * @mbggenerated
     */
    private String address;//地址

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.use_area
     *
     * @mbggenerated
     */
    private String useArea;//使用区域

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.manager_name
     *
     * @mbggenerated
     */
    private String managerName;//管理员

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.phone
     *
     * @mbggenerated
     */
    private String phone;//管理员电话号码

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.balance
     *
     * @mbggenerated
     */
    private Double balance;//组织余额

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column iot_organization.remindBalance
     *
     * @mbggenerated
     */
    private Double remindbalance;//提醒金额

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.id
     *
     * @return the value of iot_organization.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.id
     *
     * @param id the value for iot_organization.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.organization_name
     *
     * @return the value of iot_organization.organization_name
     *
     * @mbggenerated
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.organization_name
     *
     * @param organizationName the value for iot_organization.organization_name
     *
     * @mbggenerated
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName == null ? null : organizationName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.appid
     *
     * @return the value of iot_organization.appid
     *
     * @mbggenerated
     */
    public String getAppid() {
        return appid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.appid
     *
     * @param appid the value for iot_organization.appid
     *
     * @mbggenerated
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.password
     *
     * @return the value of iot_organization.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.password
     *
     * @param password the value for iot_organization.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.is_enable
     *
     * @return the value of iot_organization.is_enable
     *
     * @mbggenerated
     */
    public String getIsEnable() {
        return isEnable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.is_enable
     *
     * @param isEnable the value for iot_organization.is_enable
     *
     * @mbggenerated
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable == null ? null : isEnable.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.create_by
     *
     * @return the value of iot_organization.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.create_by
     *
     * @param createBy the value for iot_organization.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.create_date
     *
     * @return the value of iot_organization.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.create_date
     *
     * @param createDate the value for iot_organization.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.update_by
     *
     * @return the value of iot_organization.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.update_by
     *
     * @param updateBy the value for iot_organization.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.update_date
     *
     * @return the value of iot_organization.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.update_date
     *
     * @param updateDate the value for iot_organization.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.remarks
     *
     * @return the value of iot_organization.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.remarks
     *
     * @param remarks the value for iot_organization.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.status
     *
     * @return the value of iot_organization.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.status
     *
     * @param status the value for iot_organization.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.organization_id
     *
     * @return the value of iot_organization.organization_id
     *
     * @mbggenerated
     */
    public String getOrganizationId() {
        return organizationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.organization_id
     *
     * @param organizationId the value for iot_organization.organization_id
     *
     * @mbggenerated
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.address
     *
     * @return the value of iot_organization.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.address
     *
     * @param address the value for iot_organization.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.use_area
     *
     * @return the value of iot_organization.use_area
     *
     * @mbggenerated
     */
    public String getUseArea() {
        return useArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.use_area
     *
     * @param useArea the value for iot_organization.use_area
     *
     * @mbggenerated
     */
    public void setUseArea(String useArea) {
        this.useArea = useArea == null ? null : useArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.manager_name
     *
     * @return the value of iot_organization.manager_name
     *
     * @mbggenerated
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.manager_name
     *
     * @param managerName the value for iot_organization.manager_name
     *
     * @mbggenerated
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.phone
     *
     * @return the value of iot_organization.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.phone
     *
     * @param phone the value for iot_organization.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.balance
     *
     * @return the value of iot_organization.balance
     *
     * @mbggenerated
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.balance
     *
     * @param balance the value for iot_organization.balance
     *
     * @mbggenerated
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column iot_organization.remindBalance
     *
     * @return the value of iot_organization.remindBalance
     *
     * @mbggenerated
     */
    public Double getRemindbalance() {
        return remindbalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column iot_organization.remindBalance
     *
     * @param remindbalance the value for iot_organization.remindBalance
     *
     * @mbggenerated
     */
    public void setRemindbalance(Double remindbalance) {
        this.remindbalance = remindbalance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table iot_organization
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", organizationName=").append(organizationName);
        sb.append(", appid=").append(appid);
        sb.append(", password=").append(password);
        sb.append(", isEnable=").append(isEnable);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", remarks=").append(remarks);
        sb.append(", status=").append(status);
        sb.append(", organizationId=").append(organizationId);
        sb.append(", address=").append(address);
        sb.append(", useArea=").append(useArea);
        sb.append(", managerName=").append(managerName);
        sb.append(", phone=").append(phone);
        sb.append(", balance=").append(balance);
        sb.append(", remindbalance=").append(remindbalance);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}