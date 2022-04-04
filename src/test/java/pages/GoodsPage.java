package pages;

import elements.GoodElement;
import elements.ItemElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GoodsPage extends BasePage<GoodsPage> {
    private static final By GOODS_ELEMENTS = By.xpath("//*[@class='ugrid_i']");
    private static final By BASKET = By.xpath("//*[contains(@href, 'shopcart')]");
    private static final By BOOKMARKS = By.xpath("//*[contains(@href, 'bookmarks')]");
    private static final By DELETE = By.xpath("//*[text()='Удалить']");
    private static final By ITEMS = By.xpath("//*[contains(@class, 'mall-item __separator-basket __basket __flex')]");

    private final static String URL = "https://ok.ru/mall/sections/main/cn/menu";

    public GoodsPage(WebDriver driver) {
        super(driver);
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
        waitClickable(GOODS_ELEMENTS);
        //waitClickable(ITEMS);
    }

    private List<GoodElement> getGoods() {
        List<WebElement> elements = driver.findElements(GOODS_ELEMENTS);
        if (!elements.isEmpty()) {
            return elements.stream().map(goods -> new GoodElement(driver, goods).get()).toList();
        }
        return null;
    }

    public GoodElement getGoodElement(int number) {
        return Objects.requireNonNull(getGoods()).get(number).get();
    }

    public List<ItemElement> getItems() {
        List<WebElement> elements = driver.findElements(ITEMS);
        if (!elements.isEmpty()) {
            return elements.stream().map(element -> new ItemElement(driver, element)).collect(Collectors.toList());
        }
        return null;
    }

    public GoodsPage openBasket() {
        waitClickable(BASKET);
        assertionDisplayed(BASKET, "Корзина недоступна");
        click(BASKET);
        return this.get();
    }

    public void openBookmarks() {
        waitClickable(BOOKMARKS);
        assertionDisplayed(BOOKMARKS, "Закладки в разделе корзины недоступны");
        click(BOOKMARKS);
    }

    public void deleteBookmark() {
        waitClickable(DELETE);
        click(DELETE);
    }
}
