//package examms.testCases;
//
//import java.io.IOException;
//import org.openqa.selenium.By;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//import examms.page.Objects.LoginPage;
//import examms.utilities.XLUtils;
//
//public class TC_LoginDataDriven_002 extends BaseClass 
//{
//	@Test(dataProvider="LoginData")
//	public void loginDD(String user, String pwd) throws InterruptedException //case
//	{
//		LoginPage lp=new LoginPage(driver);
//		lp.setUserName(user);
//		logger.info("username provided");
//		lp.setPassword(pwd);
//		logger.info("password provided");
//		lp.clickSignIn();
//		logger.info("Clicked <Sign in>");
//		
//		Thread.sleep(3000);
//		
//		if(driver.findElement(By.className("Sidebar-content"))!= null) //hardcore for now -> Home/Main/General page object that is coming up.
//		{
//		
//		logger.info("Login passed");
//		lp.clickLogout();
//		Thread.sleep(3000);
//		}
//	else
//		{
//			
//		logger.warn("Login failed");
//		}
//	}
//	
//	@DataProvider(name = "LoginData") // dont agree with fn setup here(Must change later on)
//	String [] [] getData() throws IOException // data provider method
//	{
//		
//		String path = System.getProperty("user.dir") + "/src/test/java/examms/testData/LoginData.xlsx";
//		
//		int rownum = XLUtils.getRowCount(path, "Sheet1");
//		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);
//		
//		String logindata [][] = new String [rownum] [colnum];
//		
//		for (int x=1;x<rownum;x++)
//		{
//			for(int y=0;y<colnum;y++)
//			{
//				logindata[x-1][y]=XLUtils.getCellData(path, "Sheet1", x, y);
//			}
//		}
//	return logindata;	
//	}
//	
//	
//}
