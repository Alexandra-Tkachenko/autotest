package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.ItemGoodsPage;

public class GoodsElement extends BaseElement {
    private final By LINK = By.xpath("//*[@class='mall-item_section']/a");

    public GoodsElement(WebDriver driver, WebElement goods) {
        super(driver, goods);
        PRICE = By.xpath("//*[@class='mall-price __rub']");
        NAME = By.xpath("//*[@class='mall-title __item lp']");
    }

    public ItemGoodsPage openGoodsLink() {
        item.findElement(LINK).click();
        return new ItemGoodsPage(driver);
    }
}
