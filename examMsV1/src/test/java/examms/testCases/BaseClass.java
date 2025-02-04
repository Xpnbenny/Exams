package examms.testCases;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import examms.utilities.ReadConfig;


public class BaseClass {

	//object here
	ReadConfig readconfig=new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL(); // was hardcoded
	public String username = readconfig.getUsername(); // was hardcoded
	public String password = readconfig.getPassword(); // was hardcoded
	//public String signinbtn = readconfig.getSignInBtn(); // was hardcoded, experiment, refactor
	public static WebDriver driver;
	
	public static Logger logger;
	
	
	//prerequisites
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{	
		logger = Logger.getLogger("examms");
		PropertyConfigurator.configure("Log4j.properties");	
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(4,TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
}
