package POMClasses;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseClass {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='email']") WebElement mail;
	@FindBy(xpath="//input[@name='password']") WebElement pwd;
	@FindBy(xpath="//a[text()='Forgotten Password']") WebElement fgtpwd;
	@FindBy(xpath="//input[@type='submit']") WebElement btnlogin;
	
	public void email(String eml) {
		mail.sendKeys(eml);
	}
	
	public void password(String pass) {
		pwd.sendKeys(pass);
	}
	
	public void login() {
		btnlogin.click();
	}
	
	public void forget() {
		fgtpwd.click();
	}
	
}
