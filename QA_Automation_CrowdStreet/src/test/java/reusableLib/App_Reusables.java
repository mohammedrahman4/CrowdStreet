package reusableLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class App_Reusables {

	public static int MAX_TIMEOUT = 15;

	By IMG_LOGO = By.cssSelector("img[alt='Logo']");
	By BTN_CREATEACCOUNT = By.xpath("(//a[text()='Create An Account'])[1]");
	By EDT_EMAIL = By.cssSelector("input[id='create_account_email']");
	By EDT_FIRSTNAME = By.xpath("//*[text()='First Name']/../div/input");
	By EDT_LASTNAME = By.xpath("//*[text()='Last Name']/../div/input");
	By EDT_CREATEPASSWORD = By.xpath("//*[text()='Create a Password']/../div/input");
	By EDT_CONFIRMPASSWORD = By.xpath("//*[text()='Confirm Password']/../div/input");
	By EDT_PHONENUMBER = By.xpath("//*[text()='Phone Number']/../div/input");
	By RDO_ACCINVESTOR_YES = By.xpath("//*[text()='Are you an accredited investor?']/../../div[2]//span[text()='Yes']");
	By CHK_IAGREE = By.xpath("//*[text()='I agree to the']/../../div[1]//div");
	By CHK_IUNDERSTAND = By.xpath("//*[text()='I agree to the']/../../div[2]//div");
	By CAPTCHA_IMNOTROBOT = By.cssSelector("div[class='recaptcha-checkbox-checkmark']");
	By BTN_SIGNUP = By.xpath("//button[text()='Sign Up']");

	private WebDriver driver;

	public App_Reusables(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * Common methods
	 */

	public void launchApp(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		// Implicit Wait
		driver.manage().timeouts().implicitlyWait(MAX_TIMEOUT, TimeUnit.SECONDS);
		if (isElementPresent(IMG_LOGO)) {
			report("Application Launched succesfully", Status.PASS);
		} else {
			report("Application not Launched", Status.FAIL);
		}
	}

	public void closeApp() throws Exception {
		// Close browser
		driver.close();
		report("Browser Closed succesfully", Status.INFO);
	}

	/*
	 * Business related methods
	 */

	public void CreateAccount() throws Exception {

		String EmailID = "aaa@gmail.com";
		String FirstName = "Tester";
		String LastName = "Sample";
		String Password = "Welcome@1";

		// Click on Create An Account
		click(BTN_CREATEACCOUNT, "BTN_CREATEACCOUNT");

		// Fill Details
		set(EDT_EMAIL, EmailID, "EmailID");
		set(EDT_FIRSTNAME, FirstName, "FirstName");
		set(EDT_LASTNAME, LastName, "LastName");
		set(EDT_CREATEPASSWORD, Password, "Password");
		set(EDT_CONFIRMPASSWORD, Password, "Password");
		click(RDO_ACCINVESTOR_YES, "RDO_ACCINVESTOR_YES");
		click(CHK_IAGREE, "CHK_IAGREE");
		click(CHK_IUNDERSTAND, "CHK_IUNDERSTAND");
		clickByJs(CAPTCHA_IMNOTROBOT, "CAPTCHA_IMNOTROBOT");
		click(BTN_SIGNUP, "BTN_SIGNUP");

	}

	/*
	 * Object handling Methods
	 */

	public boolean isElementPresent(By by) {
		try {
			waitForElementToBePresent(by);
			WebElement Element = find(by);
			return Element != null;
		} catch (Exception ex) {
			return false;
		}
	}

	public void waitForElementToBePresent(By by) {
		WebDriverWait wait = new WebDriverWait(driver, MAX_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public WebElement find(By locator) {
		return driver.findElement(locator);
	}

	public void click(By by, String fieldName) throws Exception {
		if (isElementPresent(by)) {
			find(by).click();
			// TestNG report
			Reporter.log("Clicked on " + by.toString());
			report("Clicked on " + fieldName, Status.PASS);
		} else
			report(fieldName + " Object Not Present", Status.FAIL);
	}

	public void clickByJs(By by, String fieldName) throws Exception {
		if (isElementPresent(by)) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", find(by));
			report("Clicked on " + fieldName, Status.PASS);
		} else
			report(fieldName + " Object Not Present", Status.FAIL);
	}

	public void report(String Desc, Status status) {
		System.out.println(Desc + " -> " + status);
	}

	public void set(By by, String txt, String fieldName) throws Exception {
		if (isElementPresent(by)) {
			find(by).sendKeys(txt);
			report(txt + " value entered in " + fieldName + " editbox", Status.PASS);
		} else
			report(fieldName + " Object Not Present", Status.FAIL);
	}
}
