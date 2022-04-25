package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ItemGoodsPage;

public class GoodElement extends BuyElement<GoodElement> {
    protected By LINK = By.xpath("//*[@class='mall-item_section']/a");

    public GoodElement(WebDriver driver, WebElement item) {
        super(driver, item);
    }

    @Override
    protected void isLoaded() throws Error {
        check();
        waitClickable(LINK);
        assertionDisplayed(LINK, "Ссылка на товар не доступна");
    }

    public ItemGoodsPage openGoodsLink() {
        driver.findElement(LINK).click();
        return new ItemGoodsPage(driver).get();
    }
}
