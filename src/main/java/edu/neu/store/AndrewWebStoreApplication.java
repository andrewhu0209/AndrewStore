package edu.neu.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.neu.store.mappers")
public class AndrewWebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndrewWebStoreApplication.class, args);
	}

}
