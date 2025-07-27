package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement msgHeading;
	// adding Logout for DDT test
	@FindBy(xpath = "//a[@class='list-group-item'][text()='Logout']")
	WebElement lnkLogout;

	// we are verifying not validating

	public boolean isMyAccountPageExists() {
		try {
			return msgHeading.isDisplayed();
		} catch (Exception e) {
			return false;
		}
		/* Verification: This method is a verification method. It checks a state (Is the
		 "My Account" heading displayed?) and returns a boolean result. It doesn't
		 throw an error if the condition isn't met; it simply reports false. This is
		 useful when you want to make a decision in your test logic based on the
		 presence of an element (e.g., "If I am on the My Account page, then do X,
		 else do Y").
		 */

	}

	public void logout() {
		lnkLogout.click();
	}

}
