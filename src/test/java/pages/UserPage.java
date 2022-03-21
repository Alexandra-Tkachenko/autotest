package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage extends BasePage {
    private static final By GOODS = By.xpath("//*[contains(@href, 'mall/sections')]");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public GoodsPage openGoodsPage() {
        click(GOODS);
        return new GoodsPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        assertion(GOODS, WAIT_TIME, "Ссылка на товары не загрузилась");
    }
}
