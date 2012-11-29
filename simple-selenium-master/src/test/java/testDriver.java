import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.PrivateKey;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by IntelliJ IDEA.
 * User: jonaroos
 * Date: 2012-11-28
 * Time: 10:14
 */

	public class testDriver
    {
        private WebDriver driver;
        private String baseUrl;
        private String testString;
        private StringBuffer verificationErrors = new StringBuffer();

        @Before
        public void setUp() throws Exception
        {
            driver = new FirefoxDriver();
            baseUrl = "http://www.viasat.se";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void test() throws Exception
        {
            driver.get(baseUrl + "/");
            driver.findElement(By.linkText("MinaSidor")).click();
            driver.findElement(By.id("edit-name-1")).click();
            driver.findElement(By.id("edit-name-1")).clear();
            driver.findElement(By.id("edit-name-1")).sendKeys("test2@lundstedt.net");
            driver.findElement(By.id("edit-pass")).clear();
            driver.findElement(By.id("edit-pass")).sendKeys("666666");
            driver.findElement(By.id("edit-submit-1")).click();
            driver.findElement(By.linkText("Läs mer")).click();
            driver.findElement(By.xpath("//div[@id='subscription_info']/div[2]")).click();
            userPresent();
            userCredentials();
            driver.findElement(By.cssSelector("div.product_content_text")).click();
            driver.findElement(By.linkText("Logga ut")).click();
        }

        @After
        public void tearDown() throws Exception
        {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        public void userPresent()
        {
            testString = driver.findElement(By.xpath("//div[@id='subscription_info']/div[1]")).getText();
            if("Abonnentnummer: 163".equals(testString))
            {
                System.out.println("Rätt användare:");
                System.out.println(testString);
            }
            else
                System.out.println("Fel användare");
        }

        public void userCredentials()
        {
            testString = driver.findElement(By.xpath("//div[@id='subscription_info']/div[3]")).getText();
            if(testString.contains("19039063755") && testString.contains("015500268691"))
            {
                System.out.println("Mottagarenummer: 19039063755");
                System.out.println("Parabolkortsnummer: 015500268691");
            }

            else
                System.out.println("Fel användare");
        }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

