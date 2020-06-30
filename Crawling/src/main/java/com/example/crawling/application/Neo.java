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
			
			Document viewPage = Jsoup.connect("http://saneo14.com/pet2")
					.cookies(cookies)
					.execute().parse();
			
			String pets = getPets(viewPage);
			
			for (int i = 2; i < 15; i++) {
				viewPage = Jsoup.connect("http://saneo14.com/index.php?mid=pet2&page=" + i)
						.cookies(cookies)
						.execute().parse();
				
				pets = pets + getPets(viewPage);
			}
			
			pets = pets.replaceAll(",", ";");
			pets = pets.replaceAll("          <tr> \n", "");
			pets = pets.replaceAll("<tr> \n", "");
			pets = pets.replaceAll("           <td class=\"title\"> ", "");
			pets = pets.replaceAll("           <td class=\"m_no\">", "");
			pets = pets.replaceAll("</td> \n", ",");
			pets = pets.replaceAll("          </tr>", "");
			pets = pets.replaceAll("         </tbody> \n", "");
			pets = pets.replaceAll("        ", "");
			pets = pets.replaceAll("&amp;", "&");
			pets = pets.replaceAll(" class=\"hx\" ", ",");
			pets = pets.replaceAll(" </a> ", "");
			pets = pets.replaceAll("data-viewer=\"", "");
			pets = pets.replaceAll("&listStyle=viewer\"> ", ",");
			
			//System.out.println(pets);
			
			String json = "{";
			String[] pet = pets.split("\n");
			for (int i = 0; i < pet.length; i++) {
				String[] info = pet[i].split(",");
				String name = "";
				String img = "";
				String gold = "";
				String avgHp = "";
				String avgTotal = "";
				String avgAtk = "";
				String avgDef = "";
				String avgAgi = "";
				String rank = "";
				String earth = "";
				String water = "";
				String fire = "";
				String wind = "";
				String get = "";
				for (int j = 0; j < info.length; j++) {
					switch (j) {
					case 0:
						break;
					case 1:
						img = "\"img\":\"" + info[j] + "\"";
						break;
					case 2:
						name = "\"name\":\"" + info[j] + "\"";
						break;
					case 3:
						get = "\"get\":\"" + info[j] + "\"";
						break;
					case 4:
						gold = "\"gold\":\"" + info[j] + "\"";
						break;
					case 5:
						rank = "\"rank\":\"" + info[j] + "\"";
						break;
					case 6:
						earth = "\"earth\":" + info[j] + "";
						break;
					case 7:
						water = "\"water\":" + info[j] + "";
						break;
					case 8:
						fire = "\"fire\":" + info[j] + "";
						break;
					case 9:
						wind = "\"wind\":" + info[j] + "";
						break;
					case 10:
						avgAtk = "\"avgAtk\":" + info[j] + "";
						break;
					case 11:
						avgDef = "\"avgDef\":" + info[j] + "";
						break;
					case 12:
						avgAgi = "\"avgAgi\":" + info[j] + "";
						break;
					case 13:
						avgHp = "\"avgHp\":" + info[j] + "";
						break;
					case 14:
						avgTotal = "\"avgTotal\":" + info[j] + "";
						break;
					}
				}
				json = json + name + ", " + img + ", " + avgHp + ", " + avgTotal + ", " + avgAtk + ", " + avgDef + ", " + avgAgi + ", " + earth + ", " + water + ", " + fire + ", " + wind + ", " + get;
				json = json + "}\n,{";
			}
			
			json = json.substring(0, json.length() - 3);
			//System.out.println(json);
			
			viewPage = Jsoup.connect("http://saneo14.com/Information")
					.cookies(cookies)
					.execute().parse();
			
			pets = getPets(viewPage);
			
			for (int i = 2; i < 15; i++) {
				viewPage = Jsoup.connect("http://saneo14.com/index.php?mid=Information&page=" + i)
						.cookies(cookies)
						.execute().parse();
				
				pets = pets + getPets(viewPage);
			}
			
			pets = pets.replaceAll(",", ";");
			pets = pets.replaceAll("          <tr> \n", "");
			pets = pets.replaceAll("<tr> \n", "");
			pets = pets.replaceAll("           <td class=\"title\"> ", "");
			pets = pets.replaceAll("           <td class=\"m_no\">", "");
			pets = pets.replaceAll("</td> \n", ",");
			pets = pets.replaceAll("          </tr>", "");
			pets = pets.replaceAll("         </tbody> \n", "");
			pets = pets.replaceAll("        ", "");
			pets = pets.replaceAll("&amp;", "&");
			pets = pets.replaceAll(" class=\"hx\" ", ",");
			pets = pets.replaceAll(" </a> ", "");
			pets = pets.replaceAll("data-viewer=\"", "");
			pets = pets.replaceAll("&listStyle=viewer\"> ", ",");
			
			//System.out.println(pets);
			
			json = json + ",{";
			pet = pets.split("\n");
			for (int i = 0; i < pet.length; i++) {
				String[] info = pet[i].split(",");
				String name = "";
				String img = "";
				String gold = "";
				String avgHp = "";
				String avgTotal = "";
				String avgAtk = "";
				String avgDef = "";
				String avgAgi = "";
				String rank = "";
				String earth = "";
				String water = "";
				String fire = "";
				String wind = "";
				String get = "";
				for (int j = 0; j < info.length; j++) {
					switch (j) {
					case 0:
						break;
					case 1:
						img = "\"img\":\"" + info[j] + "\"";
						break;
					case 2:
						name = "\"name\":\"[환]" + info[j] + "\"";
						break;
					case 3:
						get = "\"get\":\"" + info[j] + "\"";
						break;
					case 4:
						gold = "\"gold\":\"" + info[j] + "\"";
						break;
					case 5:
						rank = "\"rank\":\"" + info[j] + "\"";
						break;
					case 6:
						earth = "\"earth\":" + info[j] + "";
						break;
					case 7:
						water = "\"water\":" + info[j] + "";
						break;
					case 8:
						fire = "\"fire\":" + info[j] + "";
						break;
					case 9:
						wind = "\"wind\":" + info[j] + "";
						break;
					case 10:
						avgAtk = "\"avgAtk\":" + info[j] + "";
						break;
					case 11:
						avgDef = "\"avgDef\":" + info[j] + "";
						break;
					case 12:
						avgAgi = "\"avgAgi\":" + info[j] + "";
						break;
					case 13:
						avgHp = "\"avgHp\":" + info[j] + "";
						break;
					case 14:
						avgTotal = "\"avgTotal\":" + info[j] + "";
						break;
					}
				}
				json = json + name + ", " + img + ", " + avgHp + ", " + avgTotal + ", " + avgAtk + ", " + avgDef + ", " + avgAgi + ", " + earth + ", " + water + ", " + fire + ", " + wind + ", " + get;
				json = json + "}\n,{";
			}
			
			json = json.substring(0, json.length() - 3);
			System.out.println(json);
			
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
		map.put("user_id", "leejoonwhan");
		map.put("password", "bluechip123");
		
		return map; 
	}
	
	public static String getPets(Document viewPage) {
		String html = viewPage.toString();
		int start = html.indexOf("<tbody style=\"line-height:1.2;\">");
		int end = html.lastIndexOf("</table>");
		String pets = html.substring(start, end);
		start = pets.indexOf("<tr>");
		pets = pets.substring(start);
		return pets;
	}

}
