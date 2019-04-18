package examms.testCases;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import examms.page.Objects.LoginPage;




public class TC_LoginTest_001 extends BaseClass 

{
	@Test
	public void loginTest() throws IOException, Exception
	{
		logger.info("URL is opened");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");
		
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickSignIn();
		logger.info("Clicked <Sign In> ");
		Thread.sleep(1000); // must refactor by implicit/explicit wait in future!  

		if(driver.findElement(By.className("Sidebar-content")) != null)
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}
}


//if(driver.findElement(By.className("Sidebar-content")) != null) //hardcore for now -> Home/Main/General page object that is coming up.
//	{
//	
//	logger.info("Login test passed");
//	
//	}
//else
//	{
//	logger.info("Login test failed");
//	}
