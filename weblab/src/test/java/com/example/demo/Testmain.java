package com.example.demo;

import org.springframework.web.client.RestTemplate;

public class Testmain {

	public static void main(String[] args) {

		String appId = "edge";
		String appSecret = "123";
		//  NetWorkHelper netHelper = new NetWorkHelper();
		String Url = String.format(
				"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appId,
				appSecret);
		//  String result = netHelper.getHttpsResponse(Url,"");
		System.out.println(Url);

		RestTemplate restTemplate = new RestTemplate();
		String url2 = "http://www.satrip.com";
		System.out.println(restTemplate.getForEntity(url2, String.class).getBody());

	}

}
