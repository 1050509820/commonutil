package com.dyzhsw.cardcontrol.response;

public enum ResultCodeEnum {
	SUCCESS("200"),ACCOUNTNOTFOUND("201"),PWDERROR("202"),CONTENTNOTFOUND("204"),
	NOTLOGIN("401"),PERMISSION("403"),NOTFOUND("404"),NONIDENTITY("405"),TIMEOUT("408"),
	INVALIDTOKEN("406"),INTERVALERROR("500");
	
	public String code;
	private ResultCodeEnum(String code){
		this.code = code;
	}
}
