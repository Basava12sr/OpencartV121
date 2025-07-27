package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	//constructor(used for all other page object classes)
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

}
