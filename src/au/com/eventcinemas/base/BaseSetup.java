package au.com.eventcinemas.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class BaseSetup {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    private void setDriver(String browserType, String url) {
        switch (browserType) {
            case "chrome":
                driver = initiateChromeDriver(url);
                break;

            case "firefox":
                driver = initiateFirefoxDriver(url);
                break;

            default:
                System.out.println("Browser : "+browserType+" not found. Launching Chrome browser...");
                driver = initiateChromeDriver(url);
        }
    }

    private WebDriver initiateFirefoxDriver(String url) {
        System.out.println("Launching Firefox browser..");
        String geckoDriverPath = "./lib/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    private WebDriver initiateChromeDriver(String url) {
        System.out.println("Launching google chrome with new profile..");
        String chromeDriverPath = "./lib/chromedriver32.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }

    @Parameters({ "browserType", "url"})
    @BeforeClass
    public void initializeBaseSetup(String browserType, String url){
        try{
            setDriver(browserType, url);
        }catch (Exception e){
            System.out.println("Error "+e);
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}
