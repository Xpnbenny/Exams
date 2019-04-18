
package examms.utilities;

//Listener class used to generate Extent reports
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time stamp
		String repName = "Test-Report-"+timeStamp+".html";
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName); // Specify location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		
		extent=new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA"); // Fixed typo found in tutorial
		extent.setSystemInfo("user", "vasko"); 
		
		htmlReporter.config().setDocumentTitle("Examms Test Project"); // Title of my report
		htmlReporter.config().setReportName("Functional Test Report"); // Name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // Location of the chart
		htmlReporter.config().setTheme(Theme.DARK);	
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // Create new entry in the report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // Send the passed information
	}
	
	public void onTestFailure(ITestResult tr)
	{
		
		logger=extent.createTest(tr.getName()); // Create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // Send the passed information
		
		String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		
		if(f.exists())
		{
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			}
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName()); // Create new entry in the report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE)); 
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
}
