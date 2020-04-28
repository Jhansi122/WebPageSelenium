package Webpage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Configurations.ConfigReaderTest;



public class EcommerceTest 
{
	WebDriver driver;
	Logger log = Logger.getLogger(EcommerceTest.class);
	
	@BeforeTest
	
	public void browser () throws InterruptedException 
	{
		ConfigReaderTest config = new ConfigReaderTest();
		
      System.setProperty("webdriver.chrome.driver", config.driver());
      log.info("opening WebBrowser");
      driver= new ChromeDriver();
     log.info("Opening URL");
      driver.get(config.URL());
      log.info("waiting for the webpage to load elements");
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      log.info("Maximizing the browser");
      driver.manage().window().maximize();
      driver.manage().deleteAllCookies();
      log.info("Idle wait for 3seconds");
      Thread.sleep(3000);
     // log.info("closing webbrowser");
   
	}
	
	@Test(priority=1)
	
	public void mouseHover()
	{
		WebElement element = driver.findElement(By.xpath("//a[@href='#'][contains(.,'Extensions Store')]"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebElement ele = driver.findElement(By.xpath("//a[@href='https://woocommerce.com/product-category/woocommerce-extensions/marketing-extensions/'][contains(.,'Marketing')]"));
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
		driver.findElement(By.xpath("//a[@data-tracks-url='https://woocommerce.com/product-category/woocommerce-extensions/marketing-extensions/promotions/']")).click();
		
		
	}
	@Test(priority=2)
	
	public void dropdown()
	{
		List<WebElement> links =  driver.findElements(By.tagName("a"));
		System.out.println("Total number of links in the webpage is "+links.size());
		for (WebElement totalLinks : links) 
		{
			System.out.println("Link text = "+totalLinks.getText());
		}
		((JavascriptExecutor)driver).executeScript("scroll(0,400)");
		
		Select dropdown= new Select(driver.findElement(By.xpath("//select[contains(@name,'orderby')]")));
		dropdown.selectByVisibleText("Sort by price: low to high");
		((JavascriptExecutor)driver).executeScript("scroll(0,400)");
		  List<WebElement> options = dropdown.getOptions();
		   for (WebElement element1 : options) 
		   {
			
			System.out.println("List elements = "+element1.getText());  
		  }
		   
	}
	
   @Test(priority = 3)

       public void addingtoCart() throws Exception
       
          {
	   driver.findElement(By.xpath("//p[contains(.,'PayPal Checkout now with Smart Payment Buttons™, dynamically displays, PayPal, Venmo, PayPal Credit, or other local payment options in a single stack giving customers the choice to pay with their preferred option.')]")).click();
	   
	  // driver.findElement(By.xpath("//button[@class='single_add_to_cart_button button alt large wccom-tracks__trackable ']")).click();
	   driver.navigate().back();
	   driver.navigate().back();
	   Thread.sleep(3000);
	   driver.close();
	  
	  
	
      }
	
	
   @Test(priority = 4)
   
     public void extentReport()
     
     {
	   ExtentHtmlReporter ext = new ExtentHtmlReporter("C:\\Program Files\\Selenium\\Spring tool suite\\Code\\Webpage.selenium\\src\\main\\resources\\Reports/Webpage.html");
	   
	   
	   
	   ExtentReports report = new ExtentReports();
	   report.attachReporter(ext);
	   
	   ExtentTest logger = report.createTest("browser");
	   
	   
	    logger.log(Status.INFO, "Opening into webpage");
	    report.flush();
	    
	    ExtentTest logger1 = report.createTest("dropdown");
	    logger1.log(Status.INFO, "Selecting from drop down");
	    report.flush();
	    
	    ExtentTest logger2 = report.createTest("addingtoCart");
	    logger2.log(Status.INFO, "Adding item to the cart");
	     report.flush();
	    
	    
	    
	       
     }
				
		
			
}

