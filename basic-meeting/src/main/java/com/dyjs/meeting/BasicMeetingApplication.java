package com.dyjs.meeting;

import com.dyjs.meeting.util.MyStringArgumentResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.dyjs.meeting.mapper")
public class BasicMeetingApplication  extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(BasicMeetingApplication.class, args);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new MyStringArgumentResolver());
    }

}
