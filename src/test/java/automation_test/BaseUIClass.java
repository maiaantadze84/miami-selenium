package automation_test;

import command_provider.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utillities.DriverFactory;
import utillities.ReadConfigFiles;


public class BaseUIClass {
    public WebDriver driver;
    public  Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    String tesCaseName = String.format("-------Test: %s------------", this.getClass().getName());
    String endTestCase = String.format("-------Test: %s------------", this.getClass().getName());

    @BeforeClass
    public void addThread() {
        ThreadContext.put("threadName",this.getClass().getName());
        driver = DriverFactory.getInstance().getDriver();
    }
    @BeforeMethod
    public void openBrowser() {
        String browserUrl = ReadConfigFiles.getPropertyValues("URL");
        System.out.println(browserUrl);
        LOGGER.info(tesCaseName);
        LOGGER.debug("Opening the URL:" + browserUrl);
        ActOn.browser(driver).openBrowser(browserUrl);
    }
    @AfterMethod
    public void closeBrowser (){
        DriverFactory.getInstance().removeDriver();
        LOGGER.info(endTestCase);
    }
}
