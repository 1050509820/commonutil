package com.basic.common.equipment.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有阀门的开关记录，发送给支付管理服务
 * @author ck
 *
 */
public class RecordOfSwitch implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String openUserNo;  //如果没有卡，cardNo=userNo
	
	private String openCardNo;	
	
	private String openUserName;
	
	private String closeUserNo;  //如果没有卡，cardNo=userNo
	
	private String closeCardNo;	
	
	private String closeUserName;
	
	private String equipmentNo;	
	
	private String equipmentName;
	
	private String equipmentType;
	
	private String equipmentChildType;
	
	//本次用水量
	private Double waterConsumption;   //如果只有阀门没有流量计，流量默认为0
	//剩余金额
	private Double balance;  //如果只有阀门没有流量计，消费金额默认为0
	//消费金额
	private Double consumptionAmount;
	//流量计底数
	private Double totalFlow;  //如果只有阀门没有流量计，底数默认为0
	
	private Date openTime;
	
	private Date closeTime;
	
	private String officeId;

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentChildType() {
		return equipmentChildType;
	}

	public void setEquipmentChildType(String equipmentChildType) {
		this.equipmentChildType = equipmentChildType;
	}

	public Double getConsumptionAmount() {
		return consumptionAmount;
	}

	public void setConsumptionAmount(Double consumptionAmount) {
		this.consumptionAmount = consumptionAmount;
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

	

	public String getOpenUserNo() {
		return openUserNo;
	}

	public void setOpenUserNo(String openUserNo) {
		this.openUserNo = openUserNo;
	}

	public String getOpenCardNo() {
		return openCardNo;
	}

	public void setOpenCardNo(String openCardNo) {
		this.openCardNo = openCardNo;
	}

	public String getOpenUserName() {
		return openUserName;
	}

	public void setOpenUserName(String openUserName) {
		this.openUserName = openUserName;
	}

	public String getCloseUserNo() {
		return closeUserNo;
	}

	public void setCloseUserNo(String closeUserNo) {
		this.closeUserNo = closeUserNo;
	}

	public String getCloseCardNo() {
		return closeCardNo;
	}

	public void setCloseCardNo(String closeCardNo) {
		this.closeCardNo = closeCardNo;
	}

	public String getCloseUserName() {
		return closeUserName;
	}

	public void setCloseUserName(String closeUserName) {
		this.closeUserName = closeUserName;
	}

	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}

	public Double getWaterConsumption() {
		return waterConsumption;
	}

	public void setWaterConsumption(Double waterConsumption) {
		this.waterConsumption = waterConsumption;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}



	public Double getTotalFlow() {
		return totalFlow;
	}

	public void setTotalFlow(Double totalFlow) {
		this.totalFlow = totalFlow;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}



	
}
