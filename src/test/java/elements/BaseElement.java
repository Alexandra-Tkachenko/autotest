package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseElement {
    protected WebDriver driver;
    protected final WebElement item;

    protected By PRICE;
    protected By NAME;

    public BaseElement(WebDriver driver, WebElement item) {
        this.driver = driver;
        this.item = item;
    }

    public WebElement getPrice() {
        return item.findElement(PRICE);
    }

    public WebElement getName() {
        return item.findElement(NAME);
    }

}
