package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Sanity","Master"})
    public void verify_account_registration() {
    	
    	logger.info("***** Starting TC001_AccountRegistrationTest *****");
    	
    	try {
        HomePage hp = new HomePage(driver);
        hp.myAcc();
        logger.info("Clicked on MyAccount Link...");
        hp.register();
        logger.info("Clicked on Register Link...");

        AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
        
        logger.info("providing customer details...");
        regpage.setFirstName(randomeString().toUpperCase());
        regpage.setLastName(randomeString().toUpperCase());
        regpage.setEmail(randomeString() + "@gmail.com");
        regpage.setPhnNum(randomNum());

        String password = randomAlphaNumeric();
        regpage.setPassword(password);
        regpage.setConfirmPass(password);

        regpage.setPrivacyPolicy();
        regpage.clickContinue();
        logger.info("Validating expected message");
        String confmsg = regpage.getConfirmationMsg();
        
        //Asset.assertequals dont log errors
        if(confmsg.equals("Your Account Has Been Created!")){
        	Assert.assertTrue(true);
        }else {
        	logger.error("Test failed");
			logger.debug("Debug logs....");
			Assert.assertTrue(false);
        }

       // Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
    }
    	catch (Exception e) {
			Assert.fail();
		}
    	logger.info("**** Finished Starting TC001_AccountRegistrationTest***** ");
    }
}
