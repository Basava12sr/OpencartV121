package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);//super method can call immediate variable/method/constructor from immediate parent ie: BasePage
	}
	
	//locators 
	@FindBy(xpath="//input[@id='input-firstname']")	
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")	
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtPhnNumber;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath ="//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy(xpath ="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	//actions
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setPhnNum(String phnNum) {
		txtPhnNumber.sendKeys(phnNum);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setConfirmPass(String confirmPass) {
		txtConfirmPassword.sendKeys(confirmPass);
	}
	
	public void setPrivacyPolicy() {
		chkAgree.click();
	}
	
	public void clickContinue() {
		btnContinue.click();
	}
	
	public String getConfirmationMsg() {
	    try {
	        return (msgConfirmation.getText());
	    } catch (Exception e) {
	        return (e.getMessage());
	    }
	}
	
	
	

}
