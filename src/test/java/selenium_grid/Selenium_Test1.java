package selenium_grid;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import utillities.DriverFactory;

public class Selenium_Test1 {
    @Test
    public void executeInAwsDocker(){
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.get("https://www.mortgagecalculator.org/");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
