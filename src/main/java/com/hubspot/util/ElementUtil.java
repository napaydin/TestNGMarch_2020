package com.hubspot.util;

import java.io.ObjectInputStream.GetField;

import org.omg.CORBA.FloatSeqHelper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Short;

public class ElementUtil {
	
//	//Utilities--Util
//    //real jobsta ElementUtil tercih edilir
//	//eger common methodlara ulasmak istiyorsak elementutil classi kullaniyoruz
//	//common methods(getTitle,click on,submit,alert handling,calling browser,getURL)
//	
//	
//	/**
//	 * This method is used to get text and handle alert
//	 * @param driver
//	 * @return
//	 */
//	public static String getAlertText(WebDriver driver){
//		Alert alert=driver.switchTo().alert();
//		String text=alert.getText();
//		System.out.println(text);
//		alert.accept();
//		return text;	
//	}
//	
//	/**
//	 *Get Title 
//	 * @param driver
//	 * @return
//	 */
//	public static String getPageTitle(WebDriver driver){
//		
//		return driver.getTitle();//string kullaniliyor
//		
//	}
//	/**
//	 * URL
//	 * @param driver
//	 * @param url
//	 */
//	public static void launchURL(WebDriver driver,String url){
//		driver.get(url);
//	}
//	
//	
//	/**
//	 * Launch Browser
//	 * @param driver
//	 * @param browserName
//	 * @return
//	 */
//	public static WebDriver launchBrowser(WebDriver driver,String browserName){
//		if (browserName.equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", "/Users/nurapaydin/Documents/Driver/chromedriver");
//			driver=new ChromeDriver();
//		}else if (browserName.equals("chrome")) {
//			System.setProperty("webdriver.gecko.driver", "/Users/nurapaydin/Documents/Driver/geckodriver");
//			driver=new FirefoxDriver();
//			
//		}
//		//kac browserimiz varsa ekleyebiliriz else if yaza yaza
//		else {
//			System.out.println("Browser is not available"+browserName);
//		}
//		return driver;
//	}
//	
//	/**
//	 * click on element
//	 * @param driver
//	 * @param locator
//	 */
//	public static void clickOn(WebDriver driver,By locator){
//		driver.findElement(locator).click();//click ()kismini degistirebiliriz
//		//sendkeys or clear metodlarini da koyabiliriz
//		
//	}
//	
//	/**
//	 *get element
//	 * @param driver
//	 * @param locator
//	 * @return
//	 */
//	public static WebElement getElement(WebDriver driver,By locator){
//		WebElement element=driver.findElement(locator);
//		return element;
//	}
//	
//	/**
//	 * Close Browser
//	 * @param driver
//	 */
//	public static void closeBrowser(WebDriver driver){
//		driver.close();
//	}
//	
//	/**
//	 * Quit Browser
//	 * @param driver
//	 */
//	public static void quitBrowser(WebDriver driver){
//		driver.quit();
//	}
//	
//	//dropDownMenu icin metod a-b
//	//metod a
//	public static void selectDropDownValueByText(WebDriver driver,By locator,String value){
//		WebElement element=getElement(driver, locator);
//		Select select=new Select(element);
//		select.selectByVisibleText(value);
//	}
//	/**
//	 * It is used to create WebElement on the basis of By locator
//	 * @param driver
//	 * @param locator
//	 * @return element
//	 */
//	//metod b
////	public static WebElement getElement(WebDriver driver,By locator){
////		WebElement element=driver.findElement(locator);
////		return element;
////	}
WebDriver driver;
	
	//Constructor
	public ElementUtil (WebDriver driver){
		this.driver = driver;
	}
	
	public WebElement getElement(By locator){
		waitForElementPresentBy(locator);
		
		WebElement element = null;
		try{
		element = driver.findElement(locator);
//		if(flash.equalsIgnoreCase("yes")){
//			JavaScriptUtil.flash(element, driver);	
//			} //sor
		
		} catch (Exception e) {
			System.out.println("some exception occured while creating webelement "+ locator);
		}
		return element;
	}
	
	public void waitForElementPresentBy(By locator){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public void doClick(By locator){
		try{
		getElement(locator).click();
		}
		catch(Exception e){
			System.out.println("Some exception occured while click on  webelement " +locator);
		}
	}

	public void doSendKeys(By locator, String value){
		try{
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
		}
		catch(Exception e){
			System.out.println("Some exception occured while sending to  webelement " + locator);
		}
	}
	
	public String doGetText(By locator){
		String text = null;
		try {
			text = getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception occured while get text to webelement "+ locator);
		}
		return text;
	}
	
	public String waitForGetPageTitle(String title){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}
	
	public boolean isElementDsiplayed(By locator){
		try{
			return getElement(locator).isDisplayed();
		}catch (Exception e) {
			System.out.println("some exception occured while checking webelement displayed "+ locator);
			return false;
		}
	}

}
