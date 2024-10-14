package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups={"Datadriven","Master"})
	public void verify_loginDDT(String email,String pwd,String exp) 
	{
		logger.info("***** Starting TC003_LoginDDT *****");
		try
		{
			HomePage hp=new HomePage(driver);
			logger.info("Clicked on MyAccount Link");
			hp.clickMyAccount();
			logger.info("Clicked on Login Link");
			hp.clickLogin();
			
			LoginPage lp=new LoginPage(driver);
			logger.info("Providing Login details...");
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clickLogin();
			
			MyAccountPage myacc=new MyAccountPage(driver);
			boolean targetPage = myacc.isMyAccountPageExists();
			
			/*Data is valid  - login success - test pass  - logout
			Data is valid -- login failed - test fail

			Data is invalid - login success - test fail  - logout
			Data is invalid -- login failed - test pass
			*/
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					Assert.assertTrue(true);
					myacc.clickLogout();
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					myacc.clickLogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
		}	
		catch(Exception e)
		{
			Assert.fail("An Exception Occured: "+e.getMessage());
		}
		
		
		logger.info("***** Finished TC003_LoginDDT *****");
	}
}

