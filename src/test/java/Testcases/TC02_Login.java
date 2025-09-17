package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTestClass;
import POMClasses.HomePage;
import POMClasses.LoginPage;
import POMClasses.MyAccountDashboard;

public class TC02_Login extends BaseTestClass{

	@Test(groups={"Sanity","Regression"})
	public void Logintest() {
		
		logger.info("Redirecting to LoginPage");
		HomePage h=new HomePage(driver);
		h.myAccount();
		h.login();
		
		logger.info("Filling valid login credentials");
		LoginPage l=new LoginPage(driver);
		l.email(p.getProperty("email"));
		l.password(p.getProperty("password"));
		l.login();
		MyAccountDashboard d=new MyAccountDashboard(driver);
		if(d.isLoginsuccessful()) {
		logger.info("Successfully logged in");
	}
		else {
			logger.error("login failed");
			logger.debug("Test debug");
		}
}
}