package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemElement extends GoodElement {

    public ItemElement(WebDriver driver, WebElement item) {
        super(driver, item);
        PRICE = By.xpath("//*[@class='mall-price __rub']");
        NAME = By.xpath("//*[@class='mall-title']");
    }

    @Override
    protected void isLoaded() throws Error {
    }
}
