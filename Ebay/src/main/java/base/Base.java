package base;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base 
{
	public static WebDriver driver;
	
	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public static ExtentTest test;

	By searchtext=By.xpath("//input[@placeholder='Search for anything']");
	By searchbtn=By.xpath("//input[@value='Search']");
	
	@BeforeSuite
	public void reportinitialize()
	{
		spark=new ExtentSparkReporter("./Reports/Ebay.html");
		spark.config().setReportName("Ebay");
		spark.config().setDocumentTitle("Ebay Functional Test");
		report=new ExtentReports();
		report.attachReporter(spark);
	}
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String str)
	{
		if(str.matches("firefox"))
		{
			driver=new FirefoxDriver();
		}
		if(str.matches("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	  
	}
	
	public void takeScreenshot(String imagename)
	{
		try {
		File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File("./Reports/images/"+imagename));
		test.addScreenCaptureFromPath("d:/dec_7PM/Ebay/Reports/images/"+imagename);
		}catch(Exception e) {};
	}
	public void openUrl(String url)
	{
		try{Thread.sleep(2000);}catch(Exception e) {}
		driver.get(url);
	}
	public void search(String value)
	{
		driver.findElement(searchtext).sendKeys(value);
		driver.findElement(searchbtn).click();
	}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	@AfterSuite
	public void saveReport()
	{
		report.flush(); //save the report
	}
}
