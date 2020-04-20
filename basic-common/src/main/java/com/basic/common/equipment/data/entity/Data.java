package com.basic.common.equipment.data.entity;

import java.io.Serializable;
import java.util.Objects;

public class Data implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double value;

	public Data() {
	}

	public Data(String name, double value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Data{" +
				"name='" + name + '\'' +
				", value=" + value +
				'}';
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

}
