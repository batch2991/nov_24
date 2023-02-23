package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.Base;

public class ResultsPage extends Base
{

	By firstprods=By.xpath("//div[@class='s-item__title']");
	By firstprices=By.xpath("//span[@class='s-item__price']");
	
	By secondprods=By.xpath("//h1[@class='x-item-title__mainTitle']");
	By secondprices=By.xpath("//span[@itemprop='price']");
	
	public String[] clickProduct(int index)
	{
		String f[]=new String[2];
		List<WebElement> prodnames=driver.findElements(firstprods);
		List<WebElement> prodprices=driver.findElements(firstprices);
		f[0]=prodnames.get(index-1).getText();
		f[1]=prodprices.get(index-1).getText().substring(1);
		
		prodnames.get(index-1).click();
		return f;		
	}
	public String[] getproductdetails()
	{
		String s[]=new String[2];
		try{Thread.sleep(2000);}catch(Exception e) {}
		ArrayList<String> tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		s[0]=driver.findElement(secondprods).getText();
		s[1]=driver.findElement(secondprices).getAttribute("content");
		return s;		
	}
}
