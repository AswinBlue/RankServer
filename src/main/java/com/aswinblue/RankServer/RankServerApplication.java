package com.aswinblue.RankServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RankServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankServerApplication.class, args);
	}

}
