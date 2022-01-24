import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class SeleniumTests {
    WebDriver driver;
    @Test
    public void firstTest() throws IOException, InterruptedException {
        String url = System.getProperty("WEB_URL");
        String browser = System.getProperty("BROWSER");
        System.out.println(url);
        System.out.println(browser);



        if(url == null)
        {
            url = "https://www.flipkart.com/";
        }

        if(browser != null && browser.contentEquals("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get(url);

        Wait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Login']/ancestor::div/div/div")));

        WebElement closeButton = driver.findElement(By.xpath("//span[text()='Login']/ancestor::div/div/div/button"));
        closeButton.click();

        System.out.println(driver.getTitle());

        takeScreenShot(driver, "./screen.png");

        // "Samsung Galaxy S10"

        WebElement searchBox = driver.findElement(By.xpath("//input[@name='q']"));
        searchBox.sendKeys("Samsung Galaxy S10");

        WebElement searchButton = driver.findElement(with(By.tagName("button")).near(searchBox));

        searchButton.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), 'Showing 1 –')]")));

        takeScreenShot(driver, "./screen2.png");

        WebElement mobilesLink = driver.findElement(By.xpath("//span[text()='CATEGORIES']//ancestor::section//a[text()='Mobiles']"));
       // mobilesLink.click();

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='Brand']//following::div[@title='SAMSUNG']")));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Brand']//following::div[@title='SAMSUNG']")));
//        WebElement filterSamsung = driver.findElement(By.xpath("//div[text()='Brand']//following::div[@title='SAMSUNG']"));

//        filterSamsung.click();

//        WebElement flipKartAssured = driver.findElement(By.xpath("//span[text()='Price']/ancestor::section/following-sibling::section/label/input"));
//        flipKartAssured.click();


        takeScreenShot(driver, "./screen2.png");
    }

    public void takeScreenShot(WebDriver driver, String pathToScrn) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File scrnFile = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrnFile, new File(pathToScrn));
    }

    @AfterTest
    public void tearDown()
    {

        driver.quit();
    }
}
