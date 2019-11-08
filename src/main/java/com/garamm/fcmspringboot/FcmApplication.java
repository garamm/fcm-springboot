package com.garamm.fcmspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.garamm.fcmspringboot.mapper"})
public class FcmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcmApplication.class, args);
	}

}
