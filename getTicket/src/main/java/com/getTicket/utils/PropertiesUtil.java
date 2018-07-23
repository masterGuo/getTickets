package com.getTicket.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: Adolph.Guo  
 * @date: 2018年7月17日 下午8:51:51 
 * @version 1.0
 * @description 
 *
 * Modification History:
 * Date         Author          Version            Description
 *---------------------------------------------------------*
 * 2018年7月17日       user           v1.0.0               修改原因
 */
public class PropertiesUtil {
    
    
    /**
     *  数据库连接配置文件
     */
    public final static  String JDBC_PROERTIES ="config/jdbc.properties";
    
    /**
     * 存放Properties实例对象的容器，使之实现“单例模式”
     */
    private static ConcurrentHashMap<String,Properties> propertiesInstanceMap = new ConcurrentHashMap<String,Properties>();
    
    /**
     * 日志
     */
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PropertiesUtil.class);

    private PropertiesUtil(){}
    
    
    /**
     * 
     * 存放Properties实例对象的容器，使之实现“单例模式”
     * @param propertiesFileName  属性文件名
     * @return Properties实例对象
     */
    private static Properties getPropertiesInstance(String propertiesFileName ){
        Properties  properties = propertiesInstanceMap.get(propertiesFileName);
        if(properties == null){
            Properties newProperties = new Properties();
            
            try {
                InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName);
                newProperties.load(is);
            }
            catch (IOException e) {
                logger.error("配置文件加载失败",e);
                return properties;
            }

            properties = newProperties;
            propertiesInstanceMap.put(propertiesFileName, properties);
        }

        return properties;
    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:26:54			
     * @param propertiesFileName
     * @param key
     * @return 			 		
     * @modify 			
     * @description 读取指定文件指定值，没有返回null			
     ************************************
     */
    public static String getStringValue(String propertiesFileName, String key) {

	Properties properties = getPropertiesInstance(propertiesFileName);
	if (properties.containsKey(key) && properties.getProperty(key) != null
		&& !properties.getProperty(key).trim().equals("")) {
	    String result = "";
	    try {
		result = new String(properties.getProperty(key).trim().getBytes("iso-8859-1"), "UTF-8");
	    } catch (UnsupportedEncodingException e) {
		logger.error("获取参数：" + key + ",转换成utf-8异常， "+e);
	    }
	    return result;
	} else {
	    logger.error("获取参数：" + key + ", 参数未配置,或者为空！");
	}
	return null;
    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:27:34			
     * @param propertiesFileName
     * @param key
     * @param defaultValue
     * @return 			 		
     * @modify 			
     * @description  读取指定文件指定值，没有返回	defaultValue		
     ************************************
     */
    public static String getStringValue(String propertiesFileName ,String key , String defaultValue)
    {
        
        Properties  properties = getPropertiesInstance(propertiesFileName);
        if(properties.containsKey(key) && properties.getProperty(key)!=null && !properties.getProperty(key).trim().equals("")){
        	 return properties.getProperty(key).trim();
        }else{
        	logger.error("获取参数："+ key +", 参数未配置,或者为空！");
        }
           
        return defaultValue;

    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:28:30			
     * @param propertiesFileName
     * @param key
     * @return 			 		
     * @modify 			
     * @description 读取指定文件指定值，没有返回0			
     ************************************
     */
    public static int getIntValue(String propertiesFileName ,String key) {
    	
        try {
            Properties  properties = getPropertiesInstance(propertiesFileName);
            if(properties.containsKey(key) && properties.getProperty(key)!=null && !properties.getProperty(key).trim().equals("")){
            	return Integer.parseInt(properties.getProperty(key).trim());
            }else{
            	logger.error("获取" + key + "值失败,未配置或者配置错误！");
            }
                
        } catch(Exception e) {
        	logger.error("获取" + key + "值失败!", e);
        }
        
        return 0;
    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:28:45			
     * @param propertiesFileName
     * @param key
     * @param defaultValue
     * @return 			 		
     * @modify 			
     * @description 读取指定文件指定值，没有返回	defaultValue			
     ************************************
     */
    public static int getIntValue(String propertiesFileName ,String key , int defaultValue) {
    	try {
            Properties  properties = getPropertiesInstance(propertiesFileName);
            if(properties.containsKey(key) && properties.getProperty(key)!=null && !properties.getProperty(key).trim().equals("")){
            	return Integer.parseInt(properties.getProperty(key).trim());
            }else{
            	logger.error("获取" + key + "值失败,未配置或者配置错误！");
            }
                
        } catch(Exception e) {
        	logger.error("获取" + key + "值失败!", e);
        }
        
        return defaultValue;
    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:28:54			
     * @param propertiesFileName
     * @param key
     * @return 			 		
     * @modify 			
     * @description 读取指定文件指定值，没有返回 0			
     ************************************
     */
    public static long getLongValue(String propertiesFileName ,String key) {
        try {
            Properties  properties = getPropertiesInstance(propertiesFileName);
            if(properties.containsKey(key) && properties.getProperty(key)!=null && !properties.getProperty(key).trim().equals("")){
            	 return Long.parseLong(properties.getProperty(key).trim());
            }else{
            	logger.error("获取" + key + "值失败,未配置或者配置错误！");
            }
               
        }catch(Exception e) {
        	logger.error("获取" + key + "值失败：", e);
        }
        
        return 0;
    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:29:09			
     * @param propertiesFileName
     * @param key
     * @return 			 		
     * @modify 			
     * @description 读取指定文件指定值，没有返回 0			
     ************************************
     */
    public static float getFloatValue(String propertiesFileName ,String key) {
        
        try {
            Properties  properties = getPropertiesInstance(propertiesFileName);
            if(properties.containsKey(key) && properties.getProperty(key)!=null && !properties.getProperty(key).trim().equals("")){
            	 return Float.parseFloat(properties.getProperty(key).trim());
            }else{
            	logger.error("获取" + key + "值失败,未配置或者配置错误！");
            }
               
        }catch(Exception e) {
        	logger.error("获取" + key + "值失败：", e);
        }
        
        return 0;
    }
    
    /**
     * @author Guoqi					
     * @date 2018年3月30日 上午10:29:25			
     * @param propertiesFileName
     * @param key
     * @return 			 		
     * @modify 			
     * @description 读取指定文件指定值，没有返回 false			
     ************************************
     */
    public static boolean getBooleanValue(String propertiesFileName ,String key) {
        try {
            Properties  properties = getPropertiesInstance(propertiesFileName);
            
            if(properties.containsKey(key) && properties.getProperty(key)!=null && !properties.getProperty(key).trim().equals("")){
           	 	return Boolean.parseBoolean(properties.getProperty(key).trim());
            }else{
            	logger.error("获取" + key + "值失败,未配置或者配置错误！");
            }
            
        }catch(Exception e) {
        	logger.error("获取" + key + "值失败：", e);
        }
        
        return false;
    }
    
    public static String getStringPropertyValues(String properties_name,String key){
    	Properties proper = null;
    	try {
    		proper = PropertiesUtil.getPropertiesInstance(properties_name);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return proper.getProperty(key).toString();
    }
    
    public static Integer getIntPropertyValues(String properties_name,String key){
    	Properties proper = null;
    	try {
    		proper = PropertiesUtil.getPropertiesInstance(properties_name);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return Integer.parseInt(proper.getProperty(key).toString());
    }
}
