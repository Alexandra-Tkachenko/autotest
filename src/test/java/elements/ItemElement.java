package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ItemElement extends BaseElement<ItemElement> {
    public ItemElement(WebDriver driver, WebElement item) {
        super(driver, item);
    }
}
