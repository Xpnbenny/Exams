package examms.page.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;
	
	public LoginPage (WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(id="username")
	WebElement txtUserName;
	
	@FindBy(id="password")
	WebElement txtPassword;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div[1]/div/div[2]/form/div[3]/button") //unsure about using xpath
	//@FindBy(xpath="btnSignIn") // experiment
	WebElement btnSignIn;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div[1]/div/div/div[2]/div/ul/li[6]/a") //not part of this page, hardcoded
	WebElement btnLogout;
	
	//Methods
	public void setUserName(String username) 
	{
		txtUserName.sendKeys(username);
	}
		
	public void setPassword(String password)
	{
		txtPassword.sendKeys(password);
	}
	
	public void clickSignIn()
	{
		btnSignIn.click();
	}
	
	public void clickLogout()
	{
		btnLogout.click();
	}
	
	
	
}
