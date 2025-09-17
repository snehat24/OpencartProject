package Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseTestClass;
import POMClasses.MyAccountDashboard;
import POMClasses.HomePage;
import POMClasses.LoginPage;
import Utilities.DataProviders;

public class TC03_LoginDatadriven extends BaseTestClass {
    @Test(dataProvider="Logindata",dataProviderClass=DataProviders.class,groups= {"Regression","Datadriven"})
	public void loginDDT(String email,String pwd,String res) {
    	
    logger.info("Execution started");
	HomePage hp =new HomePage(driver);
    hp.myAccount();
    hp.login();
    
    LoginPage lp=new LoginPage(driver);
    lp.email(email);
    lp.password(pwd);
    lp.login();
    
    MyAccountDashboard d=new MyAccountDashboard(driver);
    boolean slogin = d.isLoginsuccessful();
    
    if(res.equalsIgnoreCase("valid")) {
    	if(slogin==true) {
    	d.logout();
    	Assert.assertTrue(true);
    }
    }
    else if(res.equalsIgnoreCase("Invalid")) {
    	if(slogin==true) {
    	    logger.error("Account logged in for Invalid credentials");
    	    logger.debug("Debug logs");
    	    d.logout();
    	    Assert.assertTrue(false);
    }
    else {
    	logger.info("Login failed due to invalid credentials");
    }
    }
    logger.info("Execution completed");
    }
}