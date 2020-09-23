package com.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.service.CurrencyService;

@SpringBootTest
class WireCodingTestApplicationTests {

	
	@Autowired
	CurrencyService currencyService;
	
	@Test
	void contextLoads() {
		System.out.println(currencyService.getExchangeRate());
		
	}

}
