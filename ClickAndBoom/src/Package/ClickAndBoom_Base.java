package Package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ClickAndBoom_Base {
	protected static Properties prop;
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	static String driverPath = "C:\\Users\\lenovo\\eclipse-workspace\\chromedriver.exe";

 

	public ClickAndBoom_Base(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\Package\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void startReport(){

		//extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/CABExtentReport.html", true);
		extent = new ExtentReports ("AdninastartionPage.html");
		extent
		.addSystemInfo("Host Name", "RCAS")
		.addSystemInfo("Environment", "Automation Testing")
		.addSystemInfo("User", "SANTOSH")
		.addSystemInfo("User Name", "rcas_mithun")
		.addSystemInfo("password", "Skytrac3#");	

	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/TestsScreenshots/"+screenshotName+dateName+".png";

		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
  
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = ClickAndBoom.getScreenhot(driver, result.getName());
			//To add it in the extent report 
			logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));    
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS){
			logger.log(LogStatus.PASS, "Test Case passed is "+result.getName());
			//logger.log(LogStatus.PASS, "Test Case passed is "+result.getThrowable());
			String screenshotPath = ClickAndBoom.getScreenhot(driver, result.getName());
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		}
		extent.endTest(logger);
	}
	@AfterTest
	public void endtest(){
		extent.flush();
		extent.close();
	}
}
