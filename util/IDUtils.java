package com.basic.IoTCardPlatform.util;

import java.util.UUID;

public class IDUtils {
	
	public static String createUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");		
	}

//	public static void main(String[] args) {
////		System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
//
//	}
}
