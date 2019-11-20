package com.example.lbyanBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.spring4all.swagger.EnableSwagger2Doc;

@EnableCaching // 开启缓存
@SpringBootApplication
@EnableSwagger2Doc
public class LbyanBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LbyanBackApplication.class, args);
	}

}
