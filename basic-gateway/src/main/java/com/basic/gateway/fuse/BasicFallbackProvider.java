package com.basic.gateway.fuse;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.basic.common.equipment.entity.Result;

@Component
public class BasicFallbackProvider implements FallbackProvider {

	private static final Logger log = LoggerFactory.getLogger(BasicFallbackProvider.class);
	
	@Override
	public String getRoute() {		
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK; //请求网关成功了，所以是ok
            }
 
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }
 
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }
 
            @Override
            public void close() {
 
            }
 
            @Override
            public InputStream getBody() throws IOException {
            	log.info("业务服务未正常运行,请稍后再试");
                return new ByteArrayInputStream(JSON.toJSONString(Result.fail("业务服务未正常运行,请稍后再试")).getBytes("UTF-8")); //返回前端的内容
            }
 
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8); //设置头
                return httpHeaders;
            }
        }; 
	}

	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
 
            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }
 
            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }
 
            @Override
            public void close() {
 
            }
 
            @Override
            public InputStream getBody() throws IOException {
              	 log.info("业务服务未正常运行,请稍后再试",cause);
              	 return new ByteArrayInputStream(JSON.toJSONString(Result.fail(cause)).getBytes("UTF-8")); //返回前端的内容
            }
 
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8); //设置头
                return httpHeaders;
            }
        };
	}



}
