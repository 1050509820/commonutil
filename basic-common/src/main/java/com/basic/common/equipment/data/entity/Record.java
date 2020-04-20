package com.basic.common.equipment.data.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Record implements Serializable {

	private static final long serialVersionUID = 1L;
	private String equipmentNo;
	private Date uploadTime;
	private List<Data> Data;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Data> getData() {
		return Data;
	}

	public void setData(List<Data> data) {
		Data = data;
	}

	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}

	@Override
	public String toString() {
		return "Record{" +
				"equipmentNo='" + equipmentNo + '\'' +
				", uploadTime=" + uploadTime +
				", Data=" + Data +
				", id='" + id + '\'' +
				'}';
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}


}
