package test;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

class MSEDLFindConsumer
{
	public static WebDriver driver;
	public static void main(String [] args) throws InterruptedException
	{
		
		initialiseDriver();
		findtheConsumer();
		closeProgram();	
	}
	private static void findtheConsumer() throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebElement element= driver.findElement(By.id("consumerNo"));
		element.sendKeys("170221381559"); //A1A 903 Kumar Paradise 
		//element.sendKeys("160231208981"); // SMS got wrongly on this number
		Select select1 = new Select(driver.findElement(By.id("billingNo")));

		List<WebElement> selectall=select1.getOptions();
		System.out.println(selectall.size());
		
		for(WebElement dropdownvalue : selectall)
		{
		WebElement select= driver.findElement(By.id("billingNo"));
		Select dropdwn=new Select(select);
		//dropdwn.selectByValue(dropdownvalue.getAttribute("value"));
		dropdwn.selectByValue("0817");
		WebElement submit=driver.findElement(By.id("submitButton"));
		submit.click();
		Thread.sleep(3000);
		try {
		driver.switchTo().alert().accept();
		}
		catch(NoAlertPresentException noalert)
		{
			System.out.println("Consumer Found in "+dropdownvalue.getText());
			
			WebElement viewhistory=driver.findElement(By.xpath("//*[@id='lblViewHist']"));
			
			viewhistory.click();
			Thread.sleep(3000);
			System.out.println(driver.findElement(By.xpath("//*[@id='lblConsumerName']")).getText());
			//*[@id="lblConsumerName"]
			break;
			
		}
		}
		
		
	}
	private static void closeProgram() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(10000);
		System.out.println("closing the Driver and exiting from Program");
		driver.close();
		System.exit(0);
	}
	public static void initialiseDriver() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","/Users/prashantpawar/Documents/Development/Selenium/ChromeDriver");
		 driver=new ChromeDriver();
		//WebDriver driver1=new FirefoxDriver();
		
			driver.get("https://wss.mahadiscom.in/wss/wss?uiActionName=getViewPayBill");
			//driver.navigate().to("http:\\www.google.com");
			Thread.sleep(5000);
			//consumer no 170221381559
	
	
	}
}