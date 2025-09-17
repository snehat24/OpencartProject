package BaseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTestClass {
	public static WebDriver driver;
    public Logger logger;
    public Properties p;
    
	@BeforeClass(groups= {"Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		
		FileReader file=new FileReader("C:\\eclipse-workspace\\OpencartProject\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("No matching OS");
				return;
			}
		
		switch(br) {
		case "chrome": cap.setBrowserName("chrome");break;
		case "edge": cap.setBrowserName("MicrosoftEdge");break;
		default: System.out.println("No matching browser"); return;
		}
		driver=new RemoteWebDriver(new URL("http://10.142.106.24:4444/wd/hub"),cap);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch(br) {
		
		case "chrome":
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		driver=new ChromeDriver(options); 
		break;
		case "edge":
		System.setProperty("webdriver.edge.driver", "C:\\Users\\SNEHA\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		driver=new EdgeDriver();
		break;
		default: System.out.println("Invalid browser");
		return;
		}
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appurl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	
	@AfterClass(groups= {"Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
	

	public String randomString() {
		String rstring = RandomStringUtils.randomAlphabetic(4);
		return rstring;
	}
	
	public String randomNumber() {
		String rnumber = RandomStringUtils.randomNumeric(10);
		return rnumber;
	}
	public String randomAlphanum() {
		String ranum = RandomStringUtils.randomAlphanumeric(8);
		return ranum+"@";
	}
	
	public String captureScreen(String tname) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdate = df.format(dt);
		
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath = System.getProperty("user.dir")+"//screenshots"+tname+"_"+currentdate;
		File targetfile=new File(targetfilepath);
		srcfile.renameTo(targetfile);
		return targetfilepath;
	}

}
