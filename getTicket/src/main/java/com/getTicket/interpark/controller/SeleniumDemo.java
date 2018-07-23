package com.getTicket.interpark.controller;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumDemo {
	public static Logger logger = LoggerFactory.getLogger(SeleniumDemo.class);
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args) {  
	    	ipDemo();
	    } 
	    public static void pageDemo() {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	    	
	    	 logger.info("open: -->http://www.globalinterpark.com");   
	        WebDriver driver = new ChromeDriver();  
	        
	        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	        
	    	//WebDriver driver = new FirefoxDriver();  
	        driver.get("http://localhost:8080/getTicket/user/interpartLoginHtml"); 
	        
	        // 获取 网页的 title    
	        //logger.info("open: " + driver.getTitle()+"-->http://www.globalinterpark.com");   
	        logger.info("open: -->http://www.globalinterpark.com");   
	        /*try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        WebDriverWait webDriverWait=new WebDriverWait(driver,5);
	       // webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("kw"))).sendKeys("1806609733@qq.com");
	       // webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("su"))).click();
	        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("memEmail"))).sendKeys("1806609733@qq.com");
	        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("memPass"))).sendKeys("lang@199425728");
	        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("sign_in"))).click();

	        //driver.quit();  
	    }
	    public static void ipDemo() {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	    	System.setProperty("sun.net.client.defaultConnectTimeout", "950000");
	        System.setProperty("sun.net.client.defaultReadTimeout", "950000");
	    	 logger.info("open: -->http://www.globalinterpark.com");   
	        WebDriver driver = new ChromeDriver();  
	        
	        //driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	        
	    	//WebDriver driver = new FirefoxDriver();  
	        driver.get("http://localhost:8080/getTicket/user/interpartLoginHtml"); 
	        try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        driver.get("http://www.globalinterpark.com/detail/edetail?prdNo=18005170&dispNo=01011"); 
	       // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        // 获取 网页的 title    
	        //logger.info("open: " + driver.getTitle()+"-->http://www.globalinterpark.com");   
	        logger.info("open: -->http://www.globalinterpark.com");   
	        /*try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	        WebDriverWait webDriverWait=new WebDriverWait(driver,20,2000);
	       // webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("kw"))).sendKeys("1806609733@qq.com");
	       // webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("su"))).click();
	        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("memEmail"))).sendKeys("1806609733@qq.com");
	        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("memPass"))).sendKeys("lang@199425728");
	        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("sign_in"))).click();

	        //driver.quit();  
	    }
	    public static void baiduDemo() {
	    	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
	    	WebDriver driver = new ChromeDriver();  
	    	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    	driver.get("https://www.baidu.com");  
	    	logger.info("open: -->http://www.globalinterpark.com");   
	    	WebDriverWait webDriverWait=new WebDriverWait(driver,5);
	    	webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("kw"))).sendKeys("1806609733@qq.com");
	    	webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("su"))).click();
	    	
	    	//driver.quit(); 
	    }
	    public static void brandLogin() {
		            //设置firefox浏览器的位置
	    	System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
			
		            //创建WebDriver对象
	    	WebDriver driver = new ChromeDriver();  
			Navigation navigate = driver.navigate();
			
			//加载到指定url
			navigate.to("https://www.globalinterpark.com/user/signin");
			
			//获取输入框的id,并在输入框中输入用户名
			WebElement loginInput = driver.findElement(By.id("memEmail"));
			loginInput.sendKeys("1806609733@qq.com");
			
			//获取输入框的id，并在输入框中输入密码
			WebElement pwdInput = driver.findElement(By.id("memPass"));
			pwdInput.sendKeys("lang@199425728");
			
			//获取登陆按钮的className，并点击
			WebElement loginBtn = driver.findElement(By.id("sign_in"));
			loginBtn.click();
	    }
	}  