package com.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
//	public WebDriver driver;
//	public Properties prop;
	
	//public WebDriver initialize_driver(Properties prop) throws InterruptedException{
//		//1.yol
//		//String browser="chrome";//chrome configteki value muz
//		//burda chrome yerine firefox u da koyup configte yani tekrar testi calistirip
//		//test etmemiz gerekiyor
//		
//		//2.yol
//		String browser=prop.getProperty("browser");
//		//getProperty bize key i cagirir browser da config icinde bizim key imiz
//		
//		//if (browser.equals("chrome")) {
//			if (browser.equalsIgnoreCase("chrome")) {//takimda kimi buyuk kimi kucuk yazabilir
//				//bunu engellemek icin equalstan daha iy
//			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver();
//		//}else if (browser.equals("firefox")) { //yukaridaki gibi sebebi
//		}else if (browser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver();
//		}
//		driver.manage().window().fullscreen();//test baslarken ekran full ekran olacak
//		driver.manage().deleteAllCookies();//test baslamadan copkieleri sil
//		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
//		
//		//url git
////		driver.get("https://app.hubspot.com/login");
//		driver.get(prop.getProperty("url"));//bu bilgileri saklamak icin tercih edecegimiz
//		
//		try {
//			Thread.sleep(5000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	return driver;
//	}
//	public Properties initialize_properties(){
//		prop=new Properties();
//		
//		try {
//			FileInputStream ip=new FileInputStream("/Users/nurapaydin/Documents/workspace/FebruaryTestNG_2020/"
//					+ "src/main/java/config/hubspot/config/config.properties");//config sag click propertiesten copy paste
//			prop.load(ip);
//		} catch (IOException e) {
//			e.printStackTrace();//hata olusunca loglarda takip etmek icin
//		}
//		return prop;
//	}
	public WebDriver driver;
	public Properties prop;
	public static String flash;
	
	public WebDriver initialize_driver(Properties prop){
		
		//String browser = "chrome";
		String browser = prop.getProperty("browser");
		//getProperty()metodu her zaman key ile calisir o yuzden configdeki key dekileri koyariz
		//yani browser i value kismini degil yani chrome gibi kismi degil
		String headless = prop.getProperty("headless"); //headless
		flash=prop.getProperty("elementflash");
		if(browser.equalsIgnoreCase("chrome")){
			//baska arkadasiniz buyuk kucuk chrome yazmis olabilir onu onlemek icin
			//equalsIgnoreCase koyuyoruz
		    System.setProperty("webdriver.chrome.driver", "/Users/nurapaydin/Documents/Driver/chromedriver");
			//WebDriverManager.chromedriver().setup();
			if(headless.equalsIgnoreCase("yes")){
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			}else{
			driver = new ChromeDriver();
			}
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			if(headless.equalsIgnoreCase("yes")){
				FirefoxOptions fo = new FirefoxOptions();
				fo.addArguments("--headless");
				driver = new FirefoxDriver(fo);
			}else{
			driver = new FirefoxDriver();
			}
		}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));//burada da yine url yani key i koyduk kimse gormesin diye
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
	public Properties initialize_properties(){
		
		prop = new Properties();
		
		try {
			FileInputStream ip = new FileInputStream("/Users/nurapaydin/Documents/workspace/FebruaryTestNG_20201"
					+ "/src/main/java/config/hubspot/config/config.properties");
			//bu kismi config propertiesde sag click yapip proepertiesi seci ordan copy paste yapiyoruz 
			//userli kismi arti isaretinden onceki kismi    "user.dir" bu sekilde de yazilabilir
			//    /Users/nurapaydin/Documents/workspace/FebruaryTestNG_2020/
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public void quitBrowser(){
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("some exception occured while quitting browser");
		}
	}
	
	public void closeBrowser(){
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("some exception occured while closing browser");
		}
	}
	
	

}
