package POMClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountDashboard extends BaseClass{

	public MyAccountDashboard(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[text()='My Account']") WebElement myacc;
	@FindBy(xpath="//a[text()='Logout']") WebElement btnlogout;
	
	public boolean isLoginsuccessful() {
		try {
			return myacc.isDisplayed();
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void logout() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnlogout);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnlogout);
	}
}
