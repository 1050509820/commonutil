package com.basic.common.equipment.entity;

/**
 * 接入的设备类型 
 * 命名规则 设备类型_设备型号
 * zh 智慧水务
 * @author ck
 *
 */
public enum EquipmentType {
	
	camera_ys("camera_ys","萤石摄像头","basic-video"),
	soilmoisture_zh("soilmoisture_zh","智慧水务土壤墒情","basic-collect"),
	weatherstation_zh("weatherstation_zh","智慧水务气象站","basic-collect"),
	controller_zh("controller_zh","智慧水务控制器","basic-collect"),
	flowmeter_xt("flowmeter_xt","新天流量计","basic-collect"),
	fertilizer_xly("fertilizer_xly","星联云水肥机","basic-watermanureNetty"),
	basicvalve("basic-valve","星联云控制器","basic-valve"),
	;
	
	 String equipmentTypeCode;
	 String equipmentTypeName;
	 String feignServerName;
	 
	private EquipmentType(String equipmentTypeCode,String equipmentTypeName,String feignServerName) {
		this.equipmentTypeCode = equipmentTypeCode;
		this.equipmentTypeName = equipmentTypeName;
		this.feignServerName = feignServerName;
	}

	public String getEquipmentTypeCode() {
		return equipmentTypeCode;
	}

	public void setEquipmentTypeCode(String equipmentTypeCode) {
		this.equipmentTypeCode = equipmentTypeCode;
	}



	public String getFeignServerName() {
		return feignServerName;
	}

	public void setFeignServerName(String feignServerName) {
		this.feignServerName = feignServerName;
	}

	public String getEquipmentTypeName() {
		return equipmentTypeName;
	}

	public void setEquipmentTypeName(String equipmentTypeName) {
		this.equipmentTypeName = equipmentTypeName;
	}
	
	
}
