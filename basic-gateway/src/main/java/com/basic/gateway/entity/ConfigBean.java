package com.basic.gateway.entity;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class ConfigBean {
	
	private String secret;
	
	private List <String> passUrl;

	public List<String> getPassUrl() {
		return passUrl;
	}

	public void setPassUrl(List<String> passUrl) {
		this.passUrl = passUrl;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	
	
	
}
