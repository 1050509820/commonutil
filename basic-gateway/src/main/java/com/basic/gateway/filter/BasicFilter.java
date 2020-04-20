package com.basic.gateway.filter;




import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.basic.common.equipment.entity.Result;
import com.basic.common.equipment.entity.ResultEnum;
import com.basic.gateway.entity.ConfigBean;
import com.basic.gateway.service.PermissionService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
@EnableConfigurationProperties({ConfigBean.class})
public class BasicFilter extends ZuulFilter{

	@Autowired
	ConfigBean configBean;
	
	@Autowired
	PermissionService permissionService;
	
	private static final Logger log = LoggerFactory.getLogger(BasicFilter.class);
	
	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();     
        ctx.getResponse().setCharacterEncoding("UTF-8"); 
        String authHeader = request.getHeader("Authorization");
         
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
        	ctx.setSendZuulResponse(false); //阻止zuul分发
        	ctx.setResponseStatusCode(200);
        	ctx.setResponseBody(JSON.toJSONString(Result.freedom(ResultEnum.LOGIN_TOKEN_INVALID.getStateCode() ,"请登录!")));
        }else {
        	String token = authHeader.split(" ")[1];
        	   try {
        		   
               	Algorithm algorithm = Algorithm.HMAC256(configBean.getSecret());
                   JWTVerifier verifier = JWT.require(algorithm).build();        
                   DecodedJWT jwt = verifier.verify(token);
                   
                   Claim user = jwt.getClaim("userid");
                   Claim office = jwt.getClaim("officeid");
                   Claim no = jwt.getClaim("userNo");
                   Claim name = jwt.getClaim("username");
                   if(user != null && office != null&&no != null &&name != null) {
                       String userid = user.asString();
                       String officeid = office.asString();
                       String userNo = no.asString();
                       String username = name.asString();
                       request.getParameterMap();
                       
                       Map<String, List<String>> requestQueryParams = ctx.getRequestQueryParams();
                        
                       if (requestQueryParams == null) {
                       	requestQueryParams = new HashMap<>();
                       }
                       
                       List<String> useridParameter = new ArrayList<>();
                       useridParameter.add(userid);
                       List<String> officeidParameter = new ArrayList<>();
                       officeidParameter.add(officeid);         
                       List<String> noParameter = new ArrayList<>();
                       noParameter.add(userNo);
                       List<String> nameParameter = new ArrayList<>();
                       nameParameter.add(username);
                       requestQueryParams.put("currUserId", useridParameter);
                       requestQueryParams.put("currOfficeId", officeidParameter); 
                       requestQueryParams.put("userNo", noParameter);
                       requestQueryParams.put("username", nameParameter); 
                       ctx.setRequestQueryParams(requestQueryParams);
                       

/*                       if(!permissionService.hasPermission(token, request.getRequestURI(),userid)) {
                    	  ctx.setSendZuulResponse(false);
                          ctx.setResponseStatusCode(200);
                          ctx.setResponseBody(JSON.toJSONString(Result.freedom(ResultEnum.USER_NO_JURISDICTION.getStateCode(), "权限不足")));
                       }*/
                   }

                   
               }catch (JWTVerificationException  exception) {
       			ctx.setSendZuulResponse(false);
               	ctx.setResponseStatusCode(200);
               	ctx.setResponseBody(JSON.toJSONString(Result.freedom(ResultEnum.LOGIN_TOKEN_INVALID.getStateCode(), "token无效或者已过期")));
       		}
               
        }
		return null;
	}

	@Override
	public boolean shouldFilter() {
		
		RequestContext ctx = RequestContext.getCurrentContext();		
        HttpServletRequest request = ctx.getRequest();
        System.out.println();
        System.out.println("-----------------------------------------"+new Date()+"------------------------------");
        System.out.println("Method : "+ request.getMethod());
        System.out.println("URI : "+ request.getRequestURI());
        Enumeration<String> paraNames=request.getParameterNames();
        
        for(Enumeration<String> e=paraNames;e.hasMoreElements();){
              String thisName=e.nextElement().toString();
              String thisValue=request.getParameter(thisName);
              System.out.println(thisName+" : "+thisValue);
        }
        if(configBean.getPassUrl().contains(request.getRequestURI())) {
        	return false;
        }
        if(request.getRequestURI().startsWith("/basic-equipment/upload/")) {
        	return false;
        }
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
