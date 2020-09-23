package com.test.service;

import java.net.URI;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
	
	@Value("${currencylayer.api.key}")
    private String API_KEY;
	
	@Value("${currencylayer.api.url}")
    private String END_POINT_URL;
	
	//환율정보 가져오기.
	public String getExchangeRate() {
		
		String result = null;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    try {
			ResponseEntity<String> temp;
			URI url =new URI(END_POINT_URL +"?access_key="+API_KEY);
			
			temp = restTemplate.getForEntity(url, String.class);
			
			JSONObject resultJson = new JSONObject(temp.getBody());
			
			Boolean success = resultJson.getBoolean("success");
			
			if(success !=null && success) {
				JSONObject quoteObject  = resultJson.getJSONObject("quotes");
				result = quoteObject.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		return result;
	}
}
