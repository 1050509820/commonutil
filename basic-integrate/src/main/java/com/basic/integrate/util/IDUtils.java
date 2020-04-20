package com.basic.integrate.util;

import java.util.UUID;

public class IDUtils {
	
	public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");		
	}
}
