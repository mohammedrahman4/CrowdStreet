package reusableLib;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Core {

	public Properties prop = new Properties();
	public InputStream input;
	public WebDriver driver;
	public final String defaultbrowser = "chrome";

	@BeforeClass
	@Parameters({ "browser" })
	public void beforeClass(@Optional(defaultbrowser) String browsername) throws IOException {
		initWebDriver(browsername);
	}

	public void initWebDriver(String browsername) throws IOException {
		if (browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", getConfig("chromedriver"));
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", getConfig("iedriver"));
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.introduceFlakinessByIgnoringSecurityDomains();
			options.takeFullPageScreenshot();
			driver = new InternetExplorerDriver(options);
		}

	}

	public String getConfig(String key) throws IOException {
		input = new FileInputStream("./config.properties");
		prop.load(input);
		return prop.getProperty(key);
	}

}
