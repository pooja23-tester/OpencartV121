package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{

public static WebDriver driver;
public Logger logger;
public Properties p;	

	@BeforeClass(groups={"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//Loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "edge":new EdgeDriver();break;
		case "firefox" :new FirefoxDriver();break;
		default : System.out.println("Invalid Browser name");return;
		}
	
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL2"));//Reading url from properties file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","Master","Datadriven"})
	public void tearDown()
	{
		driver.quit();
	}
	
	@SuppressWarnings("deprecation")
	public String randomString()
	{
		String genratedstring = RandomStringUtils.randomAlphabetic(5);	
		return genratedstring;
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber()
	{
		String genratednumber = RandomStringUtils.randomNumeric(10);
		return genratednumber;
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric()
	{
		String genratedstring = RandomStringUtils.randomAlphabetic(3);
		String genratednumber = RandomStringUtils.randomNumeric(3);
		return (genratedstring+"@"+genratednumber);
	}
	
	public String captureScreen(String tname) throws IOException
	{
		/*String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		File dest=new File(System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png");
		
		FileHandler.copy(src, dest);
		
		return dest;*/
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
		
	}
	
}
