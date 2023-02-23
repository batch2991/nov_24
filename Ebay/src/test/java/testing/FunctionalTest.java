package testing;

import java.util.Arrays;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import base.Base;
import pages.HomePage;
import pages.ResultsPage;

public class FunctionalTest extends Base
{

	HomePage homepage=new HomePage();
	ResultsPage resultspage=new ResultsPage();
	@Test
	public void validateTitle()
	{
		String value="tv";
		homepage.openUrl("http://www.ebay.com");
		homepage.search(value);
		String title=homepage.getPageTitle();
		if(title.contains(value))
		{
			test=report.createTest("Validate Title");
			test.log(Status.PASS, "Title matches with exp value : "+value);
			takeScreenshot("homepage.png");
		}
		else
		{
			test=report.createTest("Validate Title");
			test.log(Status.FAIL, "Title NOT matched with exp value : "+value);
			takeScreenshot("homepage.png");
		}				
	}
	@Test
	public void validateproddetails()
	{
		String value="outdoor toys";
		resultspage.openUrl("http://www.ebay.com");
		resultspage.search(value);
		String f[]=resultspage.clickProduct(5);
		String s[]=resultspage.getproductdetails();
		if(f[0].matches(s[0]) && f[1].matches(s[1]))
		{
			test=report.createTest("Validate Product Data");
			test.log(Status.PASS, "Product details are matched  : "+Arrays.toString(s));
			takeScreenshot("results.png");
		}
		else
		{
			test=report.createTest("Validate Product Data");
			test.log(Status.FAIL, "Product details NOT  matched  : "+Arrays.toString(s));
			takeScreenshot("results.png");
		}		
	}
}
