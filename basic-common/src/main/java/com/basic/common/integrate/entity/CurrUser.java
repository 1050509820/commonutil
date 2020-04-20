package com.basic.common.integrate.entity;

import java.io.Serializable;

public class CurrUser implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String currUserId;
	private String currOfficeId;
	private String currUserNo;
	private String currUserName;
	
	
	
	public CurrUser() {
		super();
	}
	public CurrUser(String currUserId, String currOfficeId, String currUserNo, String currUserName) {
		super();
		this.currUserId = currUserId;
		this.currOfficeId = currOfficeId;
		this.currUserNo = currUserNo;
		this.currUserName = currUserName;
	}
	public String getCurrUserId() {
		return currUserId;
	}
	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}
	public String getCurrOfficeId() {
		return currOfficeId;
	}
	public void setCurrOfficeId(String currOfficeId) {
		this.currOfficeId = currOfficeId;
	}
	public String getCurrUserNo() {
		return currUserNo;
	}
	public void setCurrUserNo(String currUserNo) {
		this.currUserNo = currUserNo;
	}
	public String getCurrUserName() {
		return currUserName;
	}
	public void setCurrUserName(String currUserName) {
		this.currUserName = currUserName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
