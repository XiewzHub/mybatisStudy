package com.xiewz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xiewz.mapper")
@SpringBootApplication
public class LagouMpSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LagouMpSpringbootApplication.class, args);
	}

}
