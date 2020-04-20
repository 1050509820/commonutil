package com.basic.gateway.dao;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.basic.gateway.entity.Permission;

@FeignClient(value ="basic-integrate")
public interface EurekaClientFeign {

	@GetMapping("sys/getSysMenus")
	List<Permission> getPermission(String userid);
}
