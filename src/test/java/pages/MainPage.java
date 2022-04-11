package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage<MainPage> {
    private static final By GOODS = By.xpath("//*[contains(@href, 'mall/sections')]");
    private final static String URL = BasePage.BASE_URL;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public GoodsPage openGoodsPage() {
        click(GOODS);
        return new GoodsPage(driver).get();
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        check();
    }

    private void check() {
        assertionDisplayed(GOODS, "Ссылка на товары не загрузилась");
    }
}
