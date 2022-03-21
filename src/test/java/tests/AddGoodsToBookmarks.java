package tests;

import elements.GoodsElement;
import elements.ItemElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.GoodsPage;
import pages.LoginPage;

import java.io.IOException;
import java.time.Duration;


public class AddGoodsToBookmarks {
    public static final int MINUTES = 2;
    private static WebDriver driver;

    //@BeforeAll
    @BeforeMethod(alwaysRun = true)
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kubi1\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(MINUTES));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(MINUTES));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(MINUTES));

        driver.get("https://ok.ru/dk?st.cmd=anonymMain");
    }

    //@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @Test(invocationCount = 1)
    public void additionalBookmarks() throws IOException {
        try {
            GoodsPage goodsPage = new LoginPage(driver).login().openGoodsPage();

            GoodsElement goodsElement = goodsPage.getGoods().get(0);
            String nameGoods = goodsElement.getName().getText();
            String priceGoods = goodsElement.getPrice().getText();
            //System.out.println(priceGoods + " - " + nameGoods);

            goodsElement.openGoodsLink().addBookmark().close();
            new Actions(driver).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).build().perform();
            goodsPage.openBasket().openBookmarks();

            ItemElement itemElement = goodsPage.getItems().get(0);
            String nameItem = itemElement.getName().getText();
            String priceItem = itemElement.getPrice().getText();
            //System.out.println(priceItem + " - " + nameItem);

            Assertions.assertEquals(nameGoods, nameItem, "Имена не совпали");
            Assertions.assertEquals(priceGoods, priceItem, "Цены не совпали");

            goodsPage.deleteBookmark();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@AfterAll
    @AfterMethod(alwaysRun = true)
    static void after() {
        driver.quit();
    }
}
