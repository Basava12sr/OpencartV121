package testCases;

import java.util.Base64;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilites.DataProviders;

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class)
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("****Starting TC003_LoginDDT****");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.myAcc();
		hp.login();
		
		//Login Page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clicklogin();
		
		//My Account
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		/* Data is valid - login success -test pass - logout
		 *                 login failed -test fail
		 * Data is Invalid- login success -test fail - logout
		 *                 login failed -test pass              
		 */
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				macc.logout();
				Assert.assertTrue(true);			
				}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.logout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		}
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("****Finished TC003_LoginDDT****");
		
	}
	

}
