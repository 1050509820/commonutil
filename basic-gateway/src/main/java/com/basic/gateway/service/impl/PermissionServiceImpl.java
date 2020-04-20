package com.basic.gateway.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.basic.gateway.dao.EurekaClientFeign;
import com.basic.gateway.entity.Permission;
import com.basic.gateway.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{
	
	private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	@Autowired
	EurekaClientFeign eurekaClientFeign;
	
	
	@Override
	public boolean hasPermission(String token,String uri,String userid) {
		if(menus.containsKey(token)) {
			List<Permission> menu = menus.get(token);
			if(menu == null || menu.isEmpty()) {
				return false;
			}else {
				Permission permission = new Permission();
				permission.setRedirect(uri);
				if(menu.contains(permission)) {
					return true;
				}else {
					return false;
				}
			}
		}else {
			List<Permission> menu = getPermission(userid);
			setPermission(token, menu);
			if(menu == null || menu.isEmpty()) {
				return false;
			}else {
				Permission permission = new Permission();
				permission.setRedirect(uri);
				if(menu.contains(permission)) {
					return true;
				}else {
					return false;
				}
			}
		}
		
	}

	@Override
	public boolean setPermission(String userid, List<Permission> menu) {
		menus.put(userid, menu);
		return false;
	}

	@Override
	public List<Permission> getPermission(String userid) {
		return eurekaClientFeign.getPermission(userid);
	}
	
/*	@Scheduled(fixedRate=14400000)
	public void checkCleanPermission() {
		log.info("登录用户"+menus.size());
		menus.clear();
		log.info("清除所有用户所有权限"+menus.size());
	}*/
}
