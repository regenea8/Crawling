package com.conveniencestore.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class CUCrawling implements Crawling {
   
   @Override
   public String getHtml(int cnt) throws IOException {
          // 1. URL 선언
          String url = "http://cu.bgfretail.com/event/plusAjax.do";
          
          // 2. HTML 가져오기
          Document doc = Jsoup.connect(url)
                .header("Accept", "text/html, */*; q=0.01")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7")
                .header("Connection", "keep-alive")
                .header("Content-Length", "39")
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("Cookie", "JSESSIONID=3BTHddtPVQ7RnnVcRxLs72n0dFcz2M5dQZnzGlfTbW1RLCWGfhYN!-1873604605; _ga=GA1.2.1041260678.1570598160; _gid=GA1.2.517376641.1570598160")
                .header("Host", "cu.bgfretail.com")
                .header("Origin", "http://cu.bgfretail.com")
                .header("Referer", "http://cu.bgfretail.com/event/plus.do?category=event&depth2=1")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest")
                
                .data("pageIndex", String.valueOf(cnt++))
                .data("listType", "0")
                .data("searchCondition", "")
                
                .ignoreContentType(true) // HTML Document가 아니므로 Response의 컨텐트 타입을 무시한다.
                .post();
          
          // 3. 가져온 HTML Document 를 확인하기
          return doc.toString();
      }
   
   @Override
   public List assemble() throws IOException {
         
         String html = "";
         int cnt = 1;
         while (true) {
            
            String temp = getHtml(cnt++);
            System.out.println(temp);
            if (temp.indexOf("id=\"nothing\"")>0) {
               break;
            }
            html = html + temp;
               
         }
         ArrayList<String> strList = new ArrayList<String>();
         
         while (true) {
            int num1 = html.indexOf("<div class=\"photo\">");
            if (num1 == -1) {
               break;
            }
            
            int num2 = html.substring(num1 + 1, html.length()).indexOf("</ul> <span class=\"tag\">");
            String tag1 = html.substring(num1, num1 + num2);
            
            strList.add(tag1);
            html = html.substring(num1 + num2, html.length());
         }
         
         /*
            <div class="photo">
                 <a href="javascript:view(7084);"><img src="http://cdn2.bgfretail.com/bgfbrand/files/product/8C61AD5DFD864428920BF3F54B5A7199.jpg" alt="8C61AD5DFD864428920BF3F54B5A7199.jpg" width="180" height="180"></a>
                </div> <p class="prodName"><a href="javascript:view(7084);">2080)자일리톨치약</a></p> <p class="prodPrice"><span>3,900</span>원</p> 
                <ul> 
                 <li>1+1</li> 
          */
         
         List list = new ArrayList<HashMap<String, String>>();
         HashMap<String, String> map = null;
         
         for (String string : strList) {
            map = new HashMap<String, String>();
            String img = "";
            String name = "";
            String price = "";
            String event = "";
            
            img = string.substring(string.indexOf("http"), string.indexOf(".jpg") + 4);
            map.put("img", img);
            
            name = string.substring(string.lastIndexOf(";\">") + 3, string.lastIndexOf("</a>"));
            map.put("name", name);
            
            price = string.substring(string.indexOf("<span>") + 6, string.indexOf("</span>"));
            map.put("price", price);
            
            event = string.substring(string.indexOf("<li>") + 4, string.indexOf("</li>"));
            map.put("event", event);
            
            System.out.println(map);
            list.add(map);
         }
         
         
         return list;
      }
}
