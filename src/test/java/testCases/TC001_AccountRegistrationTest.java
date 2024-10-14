package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		logger.info("***** Starting TC001_AccountRegistrationTest ***** ");
		try
		{
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link");
			hp.clickRegister();
			logger.info("Clicked on Register Link");
			
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			logger.info("Providing customer details...");
			regpage.setFirstName(randomString().toUpperCase());
			regpage.setLastName(randomString().toUpperCase());
			regpage.setEmail(randomString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			String pwd = randomAlphaNumeric();
			regpage.setPassword(pwd);
			regpage.setConfPassword(pwd);
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
	
			logger.info("Validating expected message...");
			String confmsg=regpage.getConfirmationMsg();	
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed..");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("***** Finished TC001_AccountRegistrationTest ***** ");
	}
	
}
