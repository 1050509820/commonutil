package com.basic.common.integrate.entity;

import java.io.Serializable;
import java.util.UUID;

public class ID implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String createID(){
        return UUID.randomUUID().toString().replaceAll("-","");		
	}
}
