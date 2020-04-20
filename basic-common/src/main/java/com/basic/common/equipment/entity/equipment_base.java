package com.basic.common.equipment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class equipment_base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String equipmentNo;

	private String equipmentTypeCode;

	private String equipmentTypeName;

	private String equipmentTypeChildCode;
	
	private String equipmentTypeChildName;
	
	private String equipmentTypeCodes;

	private String parentId;

	private String userId;

	private String officeId;

	private String officeName;

	private String areaId;

	private String name;
	
	//是否可以操作
	private Integer isOperational;
	
	//是否启用报警开关
	private Integer isopen;
	//mqtt密码
	private String password;
	//在线状态(1：在线，0：离线)
	private Integer online;
	
	private String liveAddress;
	
	private String hdAddress;

	private BigDecimal pointlng;

	private BigDecimal pointlat;

	private String iconurl;

	private BigDecimal sort;

	private String groupingId;

	private String groupingName;

	private Date productionTime;

	private Date createTime;

	private Date updateTime;

	private String maxValue;

	private String minValue;

	private String switch1;

	private String switch2;

	private String switch3;

	private String switch4;
	
	private String key;
	private String deviceType;
	private String version;
	private String lotNo;
	private String serialNo;
	private String sensorName;
	private String terminalName;
	private String switchState;
	private String workTime;

	public String getSensorName() {
		return sensorName;
	}

	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getSwitchState() {
		return switchState;
	}

	public void setSwitchState(String switchState) {
		this.switchState = switchState;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	//手动上报数据标识（1）
	private String manualFlag;
	//设备连接的网关
	private String gateWay;

	public String getManualFlag() {
		return manualFlag;
	}

	public void setManualFlag(String manualFlag) {
		this.manualFlag = manualFlag;
	}
	//采集服务ip
	private String serviceIP;
	//(0：未删除 1：已删除)
	private Integer delFlag;
	
	private String validateCode;
	
	//0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
	private String direction;
	//0-慢，1-适中，2-快
	private String speed;

	public String getServiceIP() {
		return serviceIP;
	}

	public void setServiceIP(String serviceIP) {
		this.serviceIP = serviceIP;
	}

	public String getGateWay() {
		return gateWay;
	}

	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}

	public String getEquipmentTypeCode() {
		return equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}

	public String getEquipmentTypeName() {
		return equipmentTypeName;
	}

	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
	}

	public String getEquipmentTypeChildCode() {
		return equipmentTypeChildCode;
	}

	public void setEquipmentTypeChildCode(String equipmentTypeChildCode) {
		this.equipmentTypeChildCode = equipmentTypeChildCode;
	}

	public String getEquipmentTypeChildName() {
		return equipmentTypeChildName;
	}

	public void setEquipmentTypeChildName(String equipmentTypeChildName) {
		this.equipmentTypeChildName = equipmentTypeChildName;
	}
	
	public String getEquipmentTypeCodes() {
		return equipmentTypeCodes;
	}

	public void setEquipmentTypeCodes(String equipmentTypeCodes) {
		this.equipmentTypeCodes = equipmentTypeCodes;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsOperational() {
		return isOperational;
	}

	public void setIsOperational(Integer isOperational) {
		this.isOperational = isOperational;
	}

	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public BigDecimal getPointlng() {
		return pointlng;
	}

	public void setPointlng(BigDecimal pointlng) {
		this.pointlng = pointlng;
	}

	public BigDecimal getPointlat() {
		return pointlat;
	}

	public void setPointlat(BigDecimal pointlat) {
		this.pointlat = pointlat;
	}

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

	public BigDecimal getSort() {
		return sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

	public String getGroupingId() {
		return groupingId;
	}

	public void setGroupingId(String groupingId) {
		this.groupingId = groupingId;
	}

	public String getGroupingName() {
		return groupingName;
	}

	public void setGroupingName(String groupingName) {
		this.groupingName = groupingName;
	}

	public Date getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(Date productionTime) {
		this.productionTime = productionTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}
	





	public String getSwitch1() {
		return switch1;
	}

	public void setSwitch1(String switch1) {
		this.switch1 = switch1;
	}

	public String getSwitch2() {
		return switch2;
	}

	public void setSwitch2(String switch2) {
		this.switch2 = switch2;
	}

	public String getSwitch3() {
		return switch3;
	}

	public void setSwitch3(String switch3) {
		this.switch3 = switch3;
	}

	public String getSwitch4() {
		return switch4;
	}

	public void setSwitch4(String switch4) {
		this.switch4 = switch4;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	

	public String getLiveAddress() {
		return liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public String getHdAddress() {
		return hdAddress;
	}

	public void setHdAddress(String hdAddress) {
		this.hdAddress = hdAddress;
	}

	@Override
	public String toString() {
		return "equipment_base [id=" + id + ", equipmentNo=" + equipmentNo + ", equipmentTypeCode=" + equipmentTypeCode
				+ ", equipmentTypeName=" + equipmentTypeName + ", equipmentTypeChildCode=" + equipmentTypeChildCode
				+ ", equipmentTypeChildName=" + equipmentTypeChildName + ", equipmentTypeCodes=" + equipmentTypeCodes
				+ ", parentId=" + parentId + ", userId=" + userId + ", officeId=" + officeId + ", officeName="
				+ officeName + ", areaId=" + areaId + ", name=" + name + ", isopen=" + isopen + ", password=" + password
				+ ", online=" + online + ", pointlng=" + pointlng + ", pointlat=" + pointlat + ", iconurl=" + iconurl
				+ ", sort=" + sort + ", groupingId=" + groupingId + ", groupingName=" + groupingName
				+ ", productionTime=" + productionTime + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", maxValue=" + maxValue + ", minValue=" + minValue + ", switch1=" + switch1 + ", switch2=" + switch2
				+ ", switch3=" + switch3 + ", switch4=" + switch4 + ", delFlag=" + delFlag + ", validateCode="
				+ validateCode + ", direction=" + direction + ", speed=" + speed + "]";
	}
	
}
