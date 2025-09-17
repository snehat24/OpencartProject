package Testcases;


import org.testng.Assert;
import org.testng.annotations.*;

import BaseClass.BaseTestClass;
import POMClasses.HomePage;
import POMClasses.RegisterPage;

public class TC01_Register extends BaseTestClass {

	@Test(groups={"Sanity","Regression"})
	public void testRegisteration() {
		logger.info("Execution started for TC01_RegisterwithValid");
		
		HomePage h=new HomePage(driver);
		h.myAccount();
		h.registerLink();
		
	    logger.info("Redirected to register page");
	    
		RegisterPage r=new RegisterPage(driver);
		r.setfname(randomString());
		r.setlname(randomString());
		r.setphone(randomNumber());
		r.setemail(randomString()+"@gmail.com");
		String password = randomAlphanum();
	    r.setpwd(password);
	    r.setcpwd(password);
		r.checkbox();
		r.clkcontinue();
		
		logger.info("Filled details and clicked continue button");
		
		String cmsg = r.getmsg();
		if(cmsg.equals("Your Account Has Been Created!")) {
			logger.info("Account Registered successfully");
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("debug logs");
			Assert.fail();
		}
		//Assert.assertEquals(cmsg,"Your Account Has Been Created!");
		logger.info("Execution completed");
		}
	
}