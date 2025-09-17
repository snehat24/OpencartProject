package POMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BaseClass {

	public HomePage (WebDriver driver){
	super(driver);
	}
	
	@FindBy(xpath="//span[contains(text(),'Account')]") WebElement Myacc;
	@FindBy(xpath="//a[text()='Register']") WebElement reg;
	@FindBy(xpath="//a[text()='Login']") WebElement login;
	
	public void myAccount() {
		Myacc.click();
	}
	
	public void registerLink() {
		reg.click();
	}
	
	public void login() {
		login.click();
	}
	
}
