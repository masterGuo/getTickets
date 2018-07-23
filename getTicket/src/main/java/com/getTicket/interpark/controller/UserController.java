package com.getTicket.interpark.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/interpartLoginHtml")
	public String interpartLoginHtml() {
		
		return "interpartLogin.html";
	}
	@RequestMapping(value="/interpartLogin")
	@ResponseBody
	public String interpartLogin() {
		httpClientUtil2 t = new httpClientUtil2();
		String url ="https://www.globalinterpark.com/user/signinAction";
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "1806609733@qq.com");
		map.put("password", "lang@199425728");
		map.put("certTp", "06");
		String post = t.post(url,map);
		JSONArray fromObject = JSONArray.fromObject("["+post+"]");
		//url="https://www.globalinterpark.com"+fromObject.getJSONObject(0).get("LOGIN_URL").toString();
		url="http://www.globalinterpark.com/detail/edetail?prdNo=18005170&dispNo=01011";
		logger.info("url:"+url);
		post = t.post(url);
		return post;
	}
	
}
