package com.stoneage.neo;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

	public static void main(String[] args) {

		try {
			
			Connection.Response loginForm = Jsoup.connect("http://saneo14.com/index.php?mid=main&act=dispMemberLoginForm&mode=default")
					.method(Connection.Method.GET)
					.execute();
			
			Connection.Response mainPage = Jsoup.connect("http://saneo14.com/index.php?act=procMemberLogin")
					.headers(getHeaders())
					.data(getData())
					.cookies(loginForm.cookies())
					.method(Connection.Method.POST)
					.execute();
			
			Map<String, String> cookies = mainPage.cookies();

			Document viewPage = Jsoup.connect("http://saneo14.com/index.php?mid=Information&page=2")
					.cookies(cookies)
					.execute().parse();
			
			System.out.println(viewPage.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static Map<String, String> getHeaders() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		map.put("Accept-Encoding", "gzip, deflate");
		map.put("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
		map.put("Cache-Control", "max-age=0");
		map.put("Connection", "keep-alive");
		map.put("Content-Length", "317");
		map.put("Content-Type", "application/x-www-form-urlencoded");
		map.put("Cookie", "PHPSESSID=9vmuecgbpi0p6b4dbkhh1dr5c5");
		map.put("Host", "saneo14.com");
		map.put("Origin", "http://saneo14.com");
		map.put("Referer", "http://saneo14.com/index.php?mid=main&mode=d123&act=dispMemberLoginForm&mode=default");
		map.put("Upgrade-Insecure-Requests", "1");
		map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		
		return map;
	}
	
	public static Map<String, String> getData() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("error_return_url", "/index.php?mid=main&act=dispMemberLoginForm&mode=default");
		map.put("mid", "main");
		map.put("vid", "");
		map.put("ruleset", "@login");
		map.put("success_return_url", "http://saneo14.com/");
		map.put("act", "procMemberLogin");
		map.put("xe_validator_id", "modules/member/skins");
		map.put("user_id", "id");
		map.put("password", "password");
		
		return map; 
	}

}
