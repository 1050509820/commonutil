package com.basic.common.integrate.entity;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String currUserId;
	private String currUserNo;
	private String username;
	private Date createTime;
	private String officeId;
	private String type;
	//操作内容
	private String remark;
	
	
	public SysLog( String currUserId,String type, String remark) {
		super();
		this.id = ID.createID();
		this.currUserId = currUserId;
		this.createTime = new Date();
		this.type = type;
		this.remark = remark;
	}
	
	
	
	public SysLog() {
		super();
	}



	public String getCurrUserNo() {
		return currUserNo;
	}



	public void setCurrUserNo(String currUserNo) {
		this.currUserNo = currUserNo;
	}



	public String getOfficeId() {
		return officeId;
	}



	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrUserId() {
		return currUserId;
	}
	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
