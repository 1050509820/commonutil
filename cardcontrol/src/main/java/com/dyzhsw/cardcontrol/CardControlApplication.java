package com.dyzhsw.cardcontrol;

import com.dyzhsw.cardcontrol.resolver.MyStringArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.dyzhsw.cardcontrol.server.NettyServer;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableSwagger2Doc
@EnableSwagger2
public class CardControlApplication  extends WebMvcConfigurerAdapter implements CommandLineRunner{
	@Autowired
	private NettyServer nettyServer;
	public static void main(String[] args) {
		SpringApplication.run(CardControlApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		this.nettyServer.start();
	}

	/**
	 * 前端参数处理
	 * @param argumentResolvers
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(new MyStringArgumentResolver());
	}

}
