package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemGoodsPage extends BasePage<ItemGoodsPage> {
    private final By ADD_BOOKMARK = By.xpath("//*[contains(@title, 'Добавить')]");
    private final By CLOSE = By.xpath("//*[contains(@class, 'modal-new_close')]/a");

    public ItemGoodsPage(WebDriver driver) {
        super(driver);
    }

    public ItemGoodsPage addBookmark() {
        click(ADD_BOOKMARK);
        return this.get();
    }

    public void close() {
        click(CLOSE);
    }

    @Override
    protected void isLoaded() throws Error {
        check();
    }

    private void check() {
        waitClickable(ADD_BOOKMARK);
        waitClickable(CLOSE);
        assertionDisplayed(ADD_BOOKMARK, "Не добавить в закладки");
        assertionDisplayed(CLOSE, "Не закрыть");
    }
}
