package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups={"Sanity","Master"})
	public void Verify_Login()
	{
		logger.info("***** Starting TC002_LoginTest *****");
		
		try
		{
			HomePage hp=new HomePage(driver);
			logger.info("Clicked on MyAccount Link");
			hp.clickMyAccount();
			logger.info("Clicked on Login Link");
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			logger.info("Providing Login details...");
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean targetPage = myacc.isMyAccountPageExists();
			
			Assert.assertTrue(targetPage);
		}	
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC002_LoginTest *****");
	}
}
