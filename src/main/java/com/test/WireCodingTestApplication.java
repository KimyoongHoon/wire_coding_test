package com.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class WireCodingTestApplication {

	@Value("${server.msg}")
    private String str;
	
	public static void main(String[] args) {
		SpringApplication.run(WireCodingTestApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {

	    return (a) -> {
	        log.info("server config : " + str);
	    };
	};
	

}
