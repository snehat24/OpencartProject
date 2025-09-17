package POMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BaseClass{

	public RegisterPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='firstname']") WebElement firstname;
	@FindBy(xpath="//input[@name='lastname']") WebElement lastname;
	@FindBy(xpath="//input[@name='email']") WebElement email;
	@FindBy(xpath="//input[@name='telephone']") WebElement tphone;
	@FindBy(xpath="//input[@name='password']") WebElement pwd;
	@FindBy(xpath="//input[@name='confirm']") WebElement cfpwd;
	@FindBy(xpath="//input[@type='checkbox']") WebElement chkbox;
	@FindBy(xpath="//input[@type='submit']") WebElement submit;
	@FindBy(xpath="//h1[contains(text(),'Your Account Has Been Created!')]") WebElement msg;
	
	public void setfname(String fname) {
		firstname.sendKeys(fname);	
		}
	
	public void setlname(String Lname) {
		lastname.sendKeys(Lname);
	}
	
	public void setemail(String eml) {
		email.sendKeys(eml);
	}
	
	public void setphone(String tele) {
		tphone.sendKeys(tele);
	}
	
	public void setpwd(String pass) {
		pwd.sendKeys(pass);
	}
	
	public void setcpwd(String cpass) {
		cfpwd.sendKeys(cpass);
	}
	
	public void checkbox() {
		chkbox.click();
	}
	
	public void clkcontinue() {
		submit.click();
	}
	
	public String getmsg() {
		return msg.getText();
	}
	
	
}
