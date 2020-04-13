package Package;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class ClickAndBoom extends ClickAndBoom_Base{
	

	@Test(priority=1)
	public void LogInPageTest(){

		logger = extent.startTest("LogInPageTest");
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.get("https://skyweb15.skytrac.ca/");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Login");
		logger.log(LogStatus.PASS, "LogInPage Loaded properly");
	}

	@Test(priority=2)
	public void HomePageTest() throws Exception{
		logger = extent.startTest("HomePageTest");
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$UserName")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$Password")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.name("ctl00$MainContentPlaceHolder$skyWebLogin$LoginButton")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, "Home");
		logger.log(LogStatus.PASS, "HomePage Loaded properly");
	}
	@Test(priority=3)
	public void LoadDataPageTest() throws Exception{
		logger = extent.startTest("LoadDataPageTest");
		driver.findElement(By.xpath("/html/body/form/div[3]/section[2]/div[1]/table/tbody/tr[1]/td[1]/div/a")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("loadDataTitle")));
		logger.log(LogStatus.PASS, "LoadDataPage Loaded properly");
		Thread.sleep(2000);
	}



	@Test(priority=4)
	public void SkywebPageTest() throws Exception{
		logger = extent.startTest("SkywebPageTest");
		Thread.sleep(2000);
		driver.switchTo().frame("LoadDataWindow"); 
		WebElement radio1 = driver.findElement(By.id("AllRadio"));
		radio1.click();
		driver.findElement(By.id("Ok")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("SkywebVesrion")));
		logger.log(LogStatus.PASS, "SkywebHomePage Loaded properly");
		Thread.sleep(3000);
	}

	@Test(priority=5)
	public void ReloadLink() throws Exception {
		logger = extent.startTest("RealoadLinkTest");
		driver.switchTo().defaultContent();
		WebElement element = driver.findElement(By.xpath("//*[@id=\"RadMenubar_m0\"]/span"));
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		Thread.sleep(8000);
		driver.findElement(By.xpath("/html/body/form/div[4]/div[6]/ul/li[1]/div/ul/li[5]/a/span")).click();
		Thread.sleep(2000);
		driver.switchTo().frame("LoadDataWindow"); 
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("loadDataTitle")));
		logger.log(LogStatus.PASS, "ReloadLink Loaded properly");
		Thread.sleep(2000);
		driver.findElement(By.id("Cancel")).click();

	}

	@Test(priority=6)
	public void AdministraionPageTest() throws InterruptedException {
		logger = extent.startTest("AdministraionPageTest");
		driver.findElement(By.xpath("//*[@id=\"body\"]/section[2]/div[1]/table/tbody/tr[1]/td[3]/div/a/div/span[2]")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("AdministarionTitle")));
		logger.log(LogStatus.PASS, "AdministraionPage Loaded properly");
		Thread.sleep(2000);
		//	driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/span/a")).click();





	}

	@Test(priority=7)
	public void HardwaremngntPageTest() throws InterruptedException {
		logger = extent.startTest("HardwareManagementPageTest"); 
		driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/span/a")).click();
		driver.findElement(By.xpath("//*[@id=\"body\"]/section[2]/div[1]/table/tbody/tr[2]/td[1]/div/a")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("HarwaremngmntTitle")));
		logger.log(LogStatus.PASS, "HardwaremngntPage Loaded properly");
		Thread.sleep(2000);

	}

	@Test(priority=8)
	public void ReportsPageTest() throws InterruptedException {
		logger = extent.startTest("ReportsPageTest"); 
		driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/span/a")).click();
		driver.findElement(By.xpath("//*[@id=\"body\"]/section[2]/div[1]/table/tbody/tr[3]/td[1]/div/a")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("ReportsTitle")));
		logger.log(LogStatus.PASS, "ReportsPage Loaded properly");
		Thread.sleep(2000);



	}

	@Test(priority=9)
	public void DutyHoursPageTest() throws InterruptedException {
		logger = extent.startTest("DutyHoursPageTest"); 
		driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/span/a")).click();
		driver.findElement(By.xpath("//*[@id=\"body\"]/section[2]/div[1]/table/tbody/tr[3]/td[2]/div/a")).click();
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("DutyHoursTitle")));
		logger.log(LogStatus.PASS, "DutyHoursPage Loaded properly");
		Thread.sleep(2000);



	}


	@Test(priority=10)
	public void SkytracPageTest() throws InterruptedException {
		logger = extent.startTest("SkytracPageTest"); 
		driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/span/a")).click();
		driver.findElement(By.xpath("//*[@id=\"body\"]/section[2]/div[1]/table/tbody/tr[3]/td[3]/div/a")).click();
		String parent = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> it = s1.iterator(); 
		while(it.hasNext()) {
			String childwindow =it.next();

			if(!parent.equals(childwindow))
			{
				driver.switchTo().window(childwindow);
				System.out.println(driver.switchTo().window(childwindow).getTitle());
			}
		}
		Thread.sleep(3000);
		String title = driver.getTitle();
		Assert.assertEquals(title, (prop.getProperty("SkytracPageTitle")));
		logger.log(LogStatus.PASS, "SkytracPage Loaded properly");
		Thread.sleep(2000);


	}



}

