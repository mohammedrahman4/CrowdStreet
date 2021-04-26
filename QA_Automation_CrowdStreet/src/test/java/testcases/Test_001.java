package testcases;

import org.testng.annotations.Test;

import reusableLib.App_Reusables;
import reusableLib.Core;

public class Test_001 extends Core {

	@Test
	public void testcase() {
		try {
			// Initialize variables
			String Description = "To verify whether user is able to create account successfully with valid data";
			String url = "https://test.crowdstreet.com/";

			// Create Instances
			App_Reusables app = new App_Reusables(driver);

			// Launch Application
			app.launchApp(url);

			// Account creation
			app.CreateAccount();

			// Close the browser
			app.closeApp();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
