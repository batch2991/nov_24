package pages;

import base.Base;

public class HomePage extends Base
{
	public String getPageTitle()
	{
		return driver.getTitle();
	}
}
