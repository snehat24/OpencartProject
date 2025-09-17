package Utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.BaseTestClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkreporter;//to create UI of the report
	public ExtentReports report;//to add extra details to the report
	public ExtentTest test;//create test and update status
	String reportName;
	
	public void onStart(ITestContext context) {
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdate = df.format(dt);
		reportName = "Test-Report-" + currentdate +".html";
	    sparkreporter=new ExtentSparkReporter(".\\reports\\"+reportName);
	    sparkreporter.config().setDocumentTitle("OpenCart testcase Report");
	    sparkreporter.config().setTheme(Theme.DARK);      //to add theme,title for report
	    sparkreporter.config().setReportName("Opencart Functional Testcases");
	    
	    report=new ExtentReports();
	    report.attachReporter(sparkreporter);
	    report.setSystemInfo("Application", "opencart");   
	    report.setSystemInfo("Environment", "QA");
	    
	    String os = context.getCurrentXmlTest().getParameter("os");
	    report.setSystemInfo("Operating system", os);
	    
	    String browser = context.getCurrentXmlTest().getParameter("browser");
	    report.setSystemInfo("Browser", browser);
	    
	    List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
	    report.setSystemInfo("Groups", groups.toString());
	  }
	
	public void onTestSuccess(ITestResult result) {
		
	     test=report.createTest(result.getTestClass().getName()); //creating entity in report
	     test.assignCategory(result.getMethod().getGroups()); //to display groups in report
	     test.log(Status.PASS, "Test case passed "+result.getName()); // and updating status of the report
	  }
	
	public void onTestFailure(ITestResult result) {
		test=report.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.FAIL, "Test case failed "+result.getName());
	    BaseTestClass bt=new BaseTestClass();
	    String imgpath = bt.captureScreen(result.getName());
	    test.addScreenCaptureFromPath(imgpath);
	    
	  }
	
	public void onTestSkipped(ITestResult result) {
		test=report.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
	    test.log(Status.SKIP, "Test case skipped "+result.getName());
	  }
	
	public void onFinish(ITestContext context) {
	    report.flush(); //mandatory coz this only helps to push all into the file
	   String reportpath = System.getProperty("user.dir")+"\\reports\\"+ reportName;
	   File extentreport=new File(reportpath);
	   try {
		   if(extentreport.exists()) {
			   Desktop.getDesktop().browse(extentreport.toURI());//automatically launches the HTML report after test execution
			   System.out.println("Extent Report launched: " + reportpath);
	        } else {
	            System.out.println("Extent Report not found at: " + reportpath);
		   }
	} catch (IOException e) {
		e.printStackTrace();
	}
	   
	  }
}
