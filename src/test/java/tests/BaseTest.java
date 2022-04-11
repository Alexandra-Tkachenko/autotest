package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BaseTest {

    static WebDriver driver;
    protected static final int TIME = 60;

    @BeforeAll
    @Timeout(6)
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kubi1\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(TIME));
    }

    @AfterAll
    static void tearDown() {
        //driver.quit();
    }

}
