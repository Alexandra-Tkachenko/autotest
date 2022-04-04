package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.time.Duration;

public class BaseTest {

    static WebDriver driver;
    protected MainPage userMainPage;

    protected static final int MINUTES = 2;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kubi1\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(MINUTES));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(MINUTES));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(MINUTES));
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

}
