package pages;

import elements.Criteria;
import elements.GoodElement;
import elements.ItemElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class GoodsPage extends BasePage<GoodsPage> {
    private static final By GOODS_ELEMENTS = By.xpath("//*[@class='ugrid_i']");
    private static final By BASKET = By.xpath("//*[contains(@href, 'shopcart')]");
    private static final By BOOKMARKS = By.xpath("//*[contains(@href, 'bookmarks')]");
    private static final By DELETE = By.xpath("//*[text()='Удалить']");
    private static final By ITEMS = By.xpath("//*[contains(@class, 'mall-item __separator-basket __basket __flex')]");

    private static final By SEARCH_INPUT = By.xpath(".//*[contains(@placeholder, 'по товарам')]");
    private static final By MIN_PRICE = By.xpath(".//*[@name='minPrice']");
    private static final By MAX_PRICE = By.xpath(".//*[@name='maxPrice']");
    private static final By SORT_SELECT = By.xpath("//*[@id='mallsortby']");


    public GoodsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void isLoaded() throws Error {
        check();
    }

    private void check() {
        waitClickable(GOODS_ELEMENTS);
    }

    public GoodElement getGoodElementFromList(int i) {
        return Objects.requireNonNull(getGoods()).get(i).get();
    }

    public ItemElement getGoodElementFromBasket(int i) {
        return Objects.requireNonNull(getItems()).get(i).get();
    }

    private List<GoodElement> getGoods() {
        List<WebElement> elements = driver.findElements(GOODS_ELEMENTS);
        if (!elements.isEmpty()) {
            return elements.stream().map(goods -> new GoodElement(driver, goods).get()).toList();
        }
        return null;
    }

    private List<ItemElement> getItems() {
        List<WebElement> elements = driver.findElements(ITEMS);
        if (!elements.isEmpty()) {
            return elements.stream().map(goods -> new ItemElement(driver, goods).get()).toList();
        }
        return null;
    }

    public GoodsPage openBasket() {
        waitVisibility(BASKET);
        waitClickable(BASKET);
        assertionDisplayed(BASKET, "Корзина недоступна");
        click(BASKET);
        return this;
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

    public GoodsPage writeSearchQuery(String query) {
        assertionDisplayed(SEARCH_INPUT, "Поле поиска не загрузилось");
        clear(SEARCH_INPUT);
        IntStream.range(0, query.length())
                .forEach(i -> sendKeys(SEARCH_INPUT,
                        new StringBuilder().append(query.charAt(i)).toString()));
        return this.get();
    }

    public GoodsPage setMinPrice(String price) {
        waitClickable(MIN_PRICE);
        sendKeys(MIN_PRICE, price);
        return this.get();
    }

    public GoodsPage setMaxPrice(String price) {
        waitClickable(MAX_PRICE);
        sendKeys(MAX_PRICE, price);
        return this.get();
    }

    public boolean checkPrice(int minimum, int maximum) {
        waitClickable(GOODS_ELEMENTS);
        return this.getGoods().stream()
                .noneMatch(card -> (Integer.parseInt(card.getPrice().trim()) < minimum)
                        || (Integer.parseInt(card.getPrice().trim()) > maximum));
    }

    public GoodsPage chooseSort(Criteria criteria) {
        new Select(driver.findElement(SORT_SELECT)).selectByValue(criteria.get());
        return this.get();
    }

    public boolean isExpensiveSort() {
        waitVisibility(GOODS_ELEMENTS);
        List<GoodElement> goods = this.getGoods();
        return IntStream.range(0, goods.size() - 1)
                .noneMatch(i -> Integer.parseInt(goods.get(i).getPrice().trim())
                        < Integer.parseInt(goods.get(i + 1).getPrice().trim()));
    }

    public boolean isCheapSort() {
        return !isExpensiveSort();
    }
}
