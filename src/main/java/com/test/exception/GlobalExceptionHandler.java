package com.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.test.common.RestResponse;
import com.test.utils.Constants;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

	

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<RestResponse> unknownException(Exception e) {
		
		log.error("excepiton Handller 도착");
		log.error("오류 발생 : {}" , e);
		log.error("상새 내용 : {}" , e.getMessage());
		
		return new ResponseEntity<RestResponse>(
				RestResponse.builder()
				.result(Constants.RESULT.FAIL)
				.status(Constants.STATUS.ERR)
				.msg(e.getMessage())
				.build(), 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
