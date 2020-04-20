package com.basic.common.integrate.entity;

import java.io.Serializable;

public class SysUserRole implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
