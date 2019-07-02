package naveen.automate.tests;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import naveen.automate.dataprovider.TestDataProvider;
import naveen.automate.pages.Home;
import naveen.automate.pages.SignIn;
import naveen.automate.pages.UserPage;
import naveen.automate.utilitize.Utilitize;

public class Scenarios extends TestDataProvider{
	
	@Test(dataProvider = "dataRead1")
	public void runChrome(HashMap<String, Object> data) {
		
		SoftAssert softAssert = new SoftAssert();
		System.setProperty("webdriver.chrome.driver", 
				System.getProperty("user.dir") + "/src/main/resources/Drivers1/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		try {
		// you created the driver before setting the driver path
		Utilitize utilitize = new Utilitize(driver);
		Home home = new Home(utilitize);
		
		SignIn signIn = new SignIn(utilitize, softAssert);
		UserPage userPage = new UserPage(utilitize, softAssert);
		home.openUTest("https://www.utest.com/");
		home.logintoUTest();
		signIn.enterUserNameAndSubmit((String)data.get("EMAIL"), (String)data.get("PASSWORD"));
		userPage.verifyLoginPage();
		
		}catch (Exception e) {
			
		}finally {
			softAssert.assertAll();
			driver.quit();
		}
	}
	
}
