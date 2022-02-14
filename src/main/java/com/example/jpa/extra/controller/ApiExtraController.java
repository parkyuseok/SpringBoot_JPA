package com.example.jpa.extra.controller;

import java.net.URI;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.example.jpa.extra.controller
 * @fileName    : ApiExtraController.java
 * @author      : 박유석
 * @date        : 2022. 2. 14
 * @version     : 1.0 
 * <pre>
 * @description : 
 * ===========================================================
 * DATE           AUTHOR       NOTE
 * -----------------------------------------------------------
 * 2022.02.14     박유석               최초 생성
 * </pre>
 */

@RequiredArgsConstructor
@RestController
public class ApiExtraController {

	/**
	 * Q86. RestTemplate을 이용한 공공데이터포털의 공공 API 연동하여 전국 약국 목록을 가져오는 API를 작성해 보세요.
	 * - 공공데이터포털 주소: https://www.data.go.kr/
	 * - 회원가입 후 이용가능
	 * - 전국 약국 정보 조회 서비스 키워드 검색 이후 활용신청 후 조회가능
	 */
	@GetMapping("/api/extra/pharmacy")
	public String pharmacy() {
		
		String apiKey = "fKD%2FT6mKu4MOOGblx%2B%2BshA930LPL3AyvrYWtHWE%2BccF9Y8W4CwnaZnLgHRZQZGhzJ%2B5J%2BOldHeuXTV1gzSAfCA%3D%3D";
		String url = "http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey=%s&pageNo=1&numOfRows=10";
		
		String apiResult = "";
		try {
			URI uri = new URI(String.format(url, apiKey));
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			String result = restTemplate.getForObject(uri, String.class);
			apiResult = result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return apiResult;
	}
}
