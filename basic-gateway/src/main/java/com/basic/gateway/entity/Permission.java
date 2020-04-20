package com.basic.gateway.entity;

public class Permission {

	private String redirect;

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
	@Override
	public boolean equals(Object obj) {
		Permission permission = (Permission)obj;
		
		if(obj == null) {
			return false;
		}
		
		if(permission.getRedirect() == null) {
			return false;
		}
		
		if(redirect.equals(permission.getRedirect())) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
