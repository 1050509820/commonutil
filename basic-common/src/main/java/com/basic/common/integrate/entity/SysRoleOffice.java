package com.basic.common.integrate.entity;

import java.io.Serializable;

public class SysRoleOffice implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleId;

    private String officeId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

}
