package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//div[@id='content']//h1")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstname.sendKeys(fname);	
	}
	
	public void setLastName(String lname)
	{
		txtLastname.sendKeys(lname);	
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);	
	}
	
	public void setTelephone(String tphone)
	{
		txtTelephone.sendKeys(tphone);	
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);	
	}
	
	public void setConfPassword(String pwd)
	{
		txtConfPassword.sendKeys(pwd);	
	}
	
	public void setPrivacyPolicy() throws InterruptedException 
	{
		chkPolicy.click();	
	}
	
	public void clickContinue() throws InterruptedException
	{
		Thread.sleep(5000);
		btnContinue.click();	
	}
	
	public String getConfirmationMsg() throws InterruptedException 
	{
		Thread.sleep(5000);
		try
		{
			return(msgConfirmation.getText());	
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
	}
}

