package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void verify_login() {
		logger.info("*****Starting TC002_LoginTest****");
		try {
			// Homepage
			HomePage hp = new HomePage(driver);
			hp.myAcc();
			hp.login();

			// Login Page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clicklogin();

			// My Account
			MyAccountPage macc = new MyAccountPage(driver);
			boolean targetPage = macc.isMyAccountPageExists();

			Assert.assertTrue(targetPage);
		} catch (Exception e) {
			Assert.fail();
		}
		/*
		 * Validation/Assertion: A validation (or assertion) would typically be done in
		 * your test method using an assertion library (like TestNG's
		 * Assert.assertTrue() or JUnit's Assertions.assertTrue()). For example, in your
		 * test class:F
		 */
		logger.info("****Finished TC_002_Logintest****");
	}

}
