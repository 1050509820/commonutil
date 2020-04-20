package com.basic.integrate.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import com.basic.common.equipment.entity.Result;
import com.basic.common.payment.entity.UserInfo;

@FeignClient("basic-payment")
public interface PaymentFeginService {

	Result addUser(@RequestBody UserInfo userInfo); 
}
