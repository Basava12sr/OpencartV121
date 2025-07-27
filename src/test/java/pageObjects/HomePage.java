package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);//super method can call immediate variable/method/constructor from immediate parent ie: BasePage
	}
	
	//locators
		@FindBy(xpath="//span[normalize-space()='My Account']")
		WebElement lnkMyAccount;
		@FindBy(xpath="//a[normalize-space()='Register']")
		WebElement lnkRegister;
		@FindBy(xpath="//a[text()='Login']")
		WebElement lnkLogin;
		
		//Actions
		public void myAcc() {
			lnkMyAccount.click();
		}
		
		public void register() {
			lnkRegister.click();
		}
		
		public void login() {
			lnkLogin.click();
		}
}
