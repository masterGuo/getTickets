package com.getTicket.interpark.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;

public class GetHttpClient {
	private static Logger logger = LoggerFactory.getLogger(GetHttpClient.class); 
	
	public static void main(String[] args) {
		String url ="https://www.globalinterpark.com/user/signinAction";
		//getClient(url);
		Map<String, String> map = new HashMap<String, String>();
		/*String cookieStr = "wingState=visible; ipzone=cn; LANGUAGE=zh; IPCODE=002; _ga=GA1.2.924361476.1531221728; _gid=GA1.2.986147305.1531221728; WMONID=G8OSI1dIKB4; login_id=; certTp=06; memId=PHe2XhgokTfSRKhgGxBtmciJ8vOQNSKMtAXgQRqEqUE%3D; GNB_TOP_AD=done; CURRENCY=usd; recentlyEnt=18008317%2C18004552; interparkSNO_global=KbBfBhmYtbGMq%2Fc1mPyaBqDF%2BYOZ3lsTfCljBRoKEwQ%3D; tempinterparkGUEST_global=ldV6Gw%2B4m6yksR6OjkPkXg%3D%3D; memNm=rzrsYbFWchiIebA%2FZO2ePg%3D%3D; memNmLast=HSbvVaQRj43QYIxmzEL1RA%3D%3D; _gat=1";
		String[] cookieArr = cookieStr.split(";");
		for(String s : cookieArr) {
			String[] sArr = s.split("=");
			if(sArr.length==1) {
				map.put(sArr[0].trim(), "");
				continue;
			}
			map.put(sArr[0].trim(), sArr[1].trim());
		}*/
		
		map=new HashMap<String, String>();
		map.put("id", "1806609733@qq.com");
		map.put("password", "lang@199425728");
		map.put("certTp", "06");
		//{"RESULT_CODE":"000","RESULT_MESSAGE":"SUCC","RESULT":{"certTp":"06","emailYn":"N","memId":"1806609733@qq.com","sessionId":"000000201807121028091000867452","email":"1806609733@qq.com","cartNo":"20180712102809867452","memNm":"langzu","memNo":100021948945,"memNmLast":"guoqi"},"LOGIN_URL":"/main/main"}
		String JESSIONID = postClient(url, map, "UTF-8");
		
		logger.info("JESSIONID:"+JESSIONID);
	}
	
	 /**
	  * @Function: GetHttpClient.java
	  * @Description: 新版本HttpClient的post方式http连接
	  * @param url
	  * @param map
	  * @param charset
	  * @return
	  * @author: Adolph.Guo  
	  * @date: 2018年7月11日 下午9:37:47 
	  * @version 1.0
	  *
	  * @Modification_Record:
	  *     Date      Author   Version  Description
	  *------------------------------------------------*
	  * 2018年7月11日           user     v1.0.0         修改原因
	  */
	 public static String postClient(String url, Map<String, String> map, String charset) {
	        CloseableHttpClient httpClient = null;
	        HttpPost httpPost = null;
	        String result = null;
	        HttpEntity entity = null;
	        try {
	            CookieStore cookieStore = new BasicCookieStore();
	            httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	            httpPost = new HttpPost(url);
	            List<NameValuePair> list = new ArrayList<NameValuePair>();
	            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
	            while (iterator.hasNext()) {
	                Entry<String, String> elem = (Entry<String, String>) iterator.next();
	                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
	            }
	            if (list.size() > 0) {
	                UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(list, charset);
	                httpPost.setEntity(entity1);
	            }
	            CloseableHttpResponse response = httpClient.execute(httpPost);
	            entity = response.getEntity();
                //用EntityUtils.toString()这个静态方法将HttpEntity转换成字符串,防止服务器返回的数据带有中文,所以在转换的时候将字符集指定成utf-8就可以了
                String JSESSIONID = null;
                String cookie_user = null;
                List<Cookie> cookies = cookieStore.getCookies();
                for (int i = 0; i < cookies.size(); i++) {
                	/*if (cookies.get(i).getName().equals("JSESSIONID")) {
                		JSESSIONID = cookies.get(i).getValue();
                	}
                	if (cookies.get(i).getName().equals("cookie_user")) {
                		cookie_user = cookies.get(i).getValue();
                	}
                }
                if (cookie_user != null) {
                	result = JSESSIONID;*/
                	logger.info(cookies.get(i).getName()+"<->"+cookies.get(i).getValue());
                }
                String r= EntityUtils.toString(entity, "UTF-8");
                logger.info("-------------------------"+r+"-------------");
                if(response.getStatusLine().getStatusCode()==200){
                    logger.info("-----------success------------------");
                    url ="https://www.globalinterpark.com/main/main";
            		postClient(url,"["+r+"]");
                    
                    return r;
                }else{
                    logger.info(response.getStatusLine().getStatusCode()+"------------------fail-----------");
                    return null;
                }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }finally{
	            //一定要记得把entity fully consume掉，否则连接池中的connection就会一直处于占用状态
	            try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					logger.info("关闭entity失败");
				}
	            logger.info("---------------------------finally-------------");
	        }
	        return result;
	    }
	 /**
	  * @Function: GetHttpClient.java
	  * @Description: get方式http连接
	  * @param url
	  * @return
	  * @author: Adolph.Guo  
	  * @date: 2018年7月11日 下午9:37:05 
	  * @version 1.0
	  *
	  * @Modification_Record:
	  *     Date      Author   Version  Description
	  *------------------------------------------------*
	  * 2018年7月11日           user     v1.0.0         修改原因
	  */
	 public static String getClient(String url) {
			String json = "";
			HttpClient client = new HttpClient();
			GetMethod getMethod = new GetMethod(url);
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			try{
				int statusCode = client.executeMethod(getMethod);
				if(statusCode != HttpStatus.SC_OK){
					logger.error("failed to open :"+url);
					return null;
					
				}
				getMethod.getRequestHeader("Access-Control-Allow-Origin");
				getMethod.getResponseHeader("Access-Control-Allow-Origin");
				InputStream responseBodyAsStream = getMethod.getResponseBodyAsStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(responseBodyAsStream));
				String line = "";
				while((line = bufferedReader.readLine()) != null){
					json +=line;
				}
				
				//json = new String(responseBody.getBytes("GBK"), "UTF-8");
				//logger.info("json:"+json);
			}catch (Exception e) {
				logger.error("failed to open :"+url);
				return "";
			}finally{
				getMethod.releaseConnection();
			}
			logger.info("返回信息："+json);
			if(json==null||json==" "||json==""){
				return "";
			}else{
	    		    	
				return json;
			}
					
			//return new ModelAndView(new RedirectView(url));
			//return "redirect:"+url;
	}
	 /**
	  * @Function: GetHttpClient.java
	  * @Description: post方式http连接
	  * @param url
	  * @param object
	  * @return
	  * @author: Adolph.Guo  
	  * @date: 2018年7月11日 下午9:37:28 
	  * @version 1.0
	  *
	  * @Modification_Record:
	  *     Date      Author   Version  Description
	  *------------------------------------------------*
	  * 2018年7月11日           user     v1.0.0         修改原因
	  */
	 public static String postClient(String url,Object object){
			OutputStreamWriter out = null;
			BufferedReader in = null;
			String result = "";
			try{
				URL realUrl = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
				//设置通用的请求属性
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Content-Type", "application/json");
				//conn.setRequestProperty("connection", "Keep-Alive");
				//conn.setRequestProperty("user-agent","Mozilla/5.0 /Windows; U; Windows NT 4.1; de; rv:1.9.1.5) Gecko/20091102 Firefox/3.0"));
				//conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				//发送post请求必须设置一下2列
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setInstanceFollowRedirects(true);
				conn.setRequestMethod("POST");
				conn.connect();
				//获取URLConnection对象对应的输出流
				out = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
				logger.info(JSONArray.fromObject(object).toString());
				//发送请求
				out.append(JSONArray.fromObject(object).toString());
				//logger.info("发送请求成功！");
				//flush输出流的缓冲
				out.flush();
				//logger.info("flush输出流的缓冲！");
				//定义bufferedReader输入流来读取URL的响应
				InputStream inputStream = conn.getInputStream();
				//logger.info("读取URL的响应输入流");
				in = new BufferedReader(new InputStreamReader(inputStream));
				//logger.info("读取URL的响应");
				String line = "";
				while((line = in.readLine()) != null){
					result +=line;
				}
			}catch(Exception e){
				logger.error("[post请求]地址："+url+"打开失败！"+e);
			}finally{
				try{
					if(out != null){
						out.close();
					}
					if(in !=null){
						in.close();
					}
				}catch(IOException e){
					logger.error("关闭流异常！");
				}
			}
			
			if(result==null||result==" "||result==""){
				return "";
			}else{
				logger.info("result:"+result);
				return result;
			}
		}
}
