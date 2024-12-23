package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;//log4j
import org.apache.logging.log4j.Logger;   //log4j

public class BaseClass {
	
	static public WebDriver driver;
	//public WebDriver driver;// parallel testing
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	
	{
		//loading properties file
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		
		
		
		//Browser Selection
			
			switch(br.toLowerCase())
			{
			
			case "chrome":
				logger.info("Condition1");
				 WebDriverManager.chromedriver().setup();
				 driver = new ChromeDriver();
				 driver.manage().deleteAllCookies();
				 logger.info("Condition2");
				break;
				
			case "edge":
				 WebDriverManager.edgedriver().setup();
				 driver = new EdgeDriver();
				 driver.manage().deleteAllCookies();
				break;
				 
				default:
					System.out.println("Invalid Browser");
					return;
			}

		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, null);
		driver.manage().timeouts().pageLoadTimeout(10, null);
		
		driver.get("https://tutorialsninja.com/demo/index.php");
		//driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	

	public String randomeString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	
	public String randomeNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	
	public String randomAlphaNumeric()
	{
		String str=RandomStringUtils.randomAlphabetic(3);
		String num=RandomStringUtils.randomNumeric(3);
		
		return (str+"@"+num);
	}
	
	



}
