package com.basic.gateway.service;

import java.util.HashMap;
import java.util.List;

import com.basic.gateway.entity.Permission;



public interface PermissionService {
	
	final static HashMap<String, List<Permission>> menus= new HashMap<>();
	
	boolean hasPermission(String token,String uri,String userid);
	
	boolean setPermission(String userid,List<Permission> menu);
	
	List<Permission> getPermission(String userid);
	
}
