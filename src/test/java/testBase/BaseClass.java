/*package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils; // Added for robust file operations (e.g., screenshot saving)
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // Log4j Logger
	public Properties p; // Creating properties class

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// Loading config.properties file
		// Using File.separator for platform-independent path
		FileReader file = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass()); // Log4j Logger setup

		// Determine execution environment (remote or local)
		String executionEnv = p.getProperty("execution_env").toLowerCase();

		if (executionEnv.equalsIgnoreCase("remote")) {
			// For remote execution (e.g., Selenium Grid)

			// Set browser capability
			switch (br.toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.setPlatformName(os.equalsIgnoreCase("mac") ? "MAC" : "WIN11");
				try {
					driver = new RemoteWebDriver(new URL("http://10.201.196.188:4444"), chromeOptions);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				edgeOptions.setPlatformName(os.equalsIgnoreCase("mac") ? "MAC" : "WIN11");
				try {
					driver = new RemoteWebDriver(new URL("http://10.201.196.188:4444"), edgeOptions);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "firefox":
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.setPlatformName(os.equalsIgnoreCase("mac") ? "MAC" : "WIN11");
				try {
					driver = new RemoteWebDriver(new URL("http://10.201.196.188:4444"), firefoxOptions);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("No matching browser specified for remote execution: " + br);
				return; // Exit setup if browser is not recognized
			}

		} else if (executionEnv.equalsIgnoreCase("local")) {
			// For local execution
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name specified for local execution: " + br);
				return; // Exit setup if browser is not recognized
			}
		} else {
			// Handle unknown execution environment
			System.out.println(
					"Invalid 'execution_env' specified in config.properties: " + p.getProperty("execution_env"));
			return; // Exit setup if environment is not recognized
		}

		// Common driver settings applied after driver initialization
		if (driver != null) { // Ensure driver is initialized before performing actions
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();
		} else {
			System.out.println("Driver could not be initialized. Please check your configuration.");
		}
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearrdown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Create predefined class for string
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNum() {
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNum = RandomStringUtils.randomNumeric(7); // Changed to 7 to make total length 10 (3 alpha + 7
																	// numeric)
		return (generatedString + "@" + generatedNum);
	}

	// captureScreen for extent report manager for on failure method
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		// Using File.separator for platform-independent path
		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		// Use FileUtils.copyFile for more robust file copying
		FileUtils.copyFile(sourceFile, targetFile);

		return targetFilePath;
	}
}
*/
























/*package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils; // Added for robust file operations (e.g., screenshot saving)
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // Log4j Logger
	public Properties p; // Creating properties class

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		// Loading config.properties file
		// Using File.separator for platform-independent path
		FileReader file = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass()); // Log4j Logger setup

		// Determine execution environment (remote or local)
		String executionEnv = p.getProperty("execution_env").toLowerCase();

		if (executionEnv.equalsIgnoreCase("remote")) {
			// For remote execution (e.g., Selenium Grid)
			// DesiredCapabilities is a class in Selenium that allows you to specify the
			// properties (like browser, OS, version, etc.) of the browser you want to
			// automate on the remote machine
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// Set OS capability
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching OS specified for remote execution: " + os);
				return; // Exit setup if OS is not recognized
			}

			// Set browser capability
			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser specified for remote execution: " + br);
				return; // Exit setup if browser is not recognized
			}

			// Initialize RemoteWebDriver with exception handling
			try {
				// Corrected the URL: Removed leading space and added "/wd/hub" for Selenium Grid 4
				//driver = new RemoteWebDriver(new URL("http://10.201.196.188:4444/wd/hub"), capabilities);
				driver = new RemoteWebDriver(new URL("http://10.201.196.188:4444"), capabilities);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (executionEnv.equalsIgnoreCase("local")) {
			// For local execution
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name specified for local execution: " + br);
				return; // Exit setup if browser is not recognized
			}
		} else {
			// Handle unknown execution environment
			System.out.println(
					"Invalid 'execution_env' specified in config.properties: " + p.getProperty("execution_env"));
			return; // Exit setup if environment is not recognized
		}

		// Common driver settings applied after driver initialization
		if (driver != null) { // Ensure driver is initialized before performing actions
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();
		} else {
			System.out.println("Driver could not be initialized. Please check your configuration.");
		}
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearrdown() {
		if (driver != null) {
			driver.quit();
		}
	}

	// Create predefined class for string
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNum() {
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNum = RandomStringUtils.randomNumeric(7); // Changed to 7 to make total length 10 (3 alpha + 7
																	// numeric)
		return (generatedString + "@" + generatedNum);
	}

	// captureScreen for extent report manager for on failure method
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		// Using File.separator for platform-independent path
		String targetFilePath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + tname
				+ "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		// Use FileUtils.copyFile for more robust file copying
		FileUtils.copyFile(sourceFile, targetFile);

		return targetFilePath;
	}
}
*/

package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setup(String os, String br) throws IOException {

		FileReader file = new FileReader(System.getProperty("user.dir") + File.separator + "src" + File.separator
				+ "test" + File.separator + "resources" + File.separator + "config.properties");
		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		String executionEnv = p.getProperty("execution_env").toLowerCase();

		if (executionEnv.equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// ✅ Added Linux OS support
			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else {
				System.out.println("No matching OS specified for remote execution: " + os);
				return;
			}

			switch (br.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser specified for remote execution: " + br);
				return;
			}

			try {
				driver = new RemoteWebDriver(new URL("http://10.201.196.188:4444"), capabilities);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (executionEnv.equalsIgnoreCase("local")) {
			switch (br.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			default:
				System.out.println("Invalid browser name specified for local execution: " + br);
				return;
			}
		} else {
			System.out.println(
					"Invalid 'execution_env' specified in config.properties: " + p.getProperty("execution_env"));
			return;
		}

		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));
			driver.manage().window().maximize();
		} else {
			System.out.println("Driver could not be initialized. Please check your configuration.");
		}
	}

	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearrdown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNum() {
		String generatedNum = RandomStringUtils.randomNumeric(10);
		return generatedNum;
	}

	public String randomAlphaNumeric() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		String generatedNum = RandomStringUtils.randomNumeric(7);
		return (generatedString + "@" + generatedNum);
	}

	public String captureScreen(String testName) {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ testName + "_" + timestamp + ".png";

		try {
			if (driver != null) {
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File(screenshotPath));
			} else {
				System.out.println("❌ Cannot take screenshot — WebDriver is null.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return screenshotPath;
	}
}
