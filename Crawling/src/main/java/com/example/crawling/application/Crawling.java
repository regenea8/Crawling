package com.example.crawling.application;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Crawling {

	public static void main(String[] args) {
		
		try {
		    // 1. URL 선언
		    String url = "http://www.daum.net";
		    
		    // 2. HTML 가져오기
		    Document doc = Jsoup.connect(url).get();
		    
		    // 3. 가져온 HTML Document 를 확인하기
		    System.out.println(doc.toString());
		} catch (IOException e) {
		    // Exp : Connection Fail
		    e.printStackTrace();
		}

	}

}
