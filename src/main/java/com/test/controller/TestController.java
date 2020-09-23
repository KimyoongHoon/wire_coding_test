package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.common.RestResponse;
import com.test.service.CurrencyService;
import com.test.utils.Constants;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	CurrencyService currencyService;
	
	@RequestMapping(value = "/getCurrency", method = RequestMethod.GET)
    public ResponseEntity<RestResponse> getCurrency() {
		
		
		return ResponseEntity.ok(
				RestResponse.builder()
				.result(Constants.RESULT.SUCCESS)
				.status(Constants.STATUS.OK)
				.msg(currencyService.getExchangeRate())
				.build()
		);	
	}	
	
	
	
	
	
}
