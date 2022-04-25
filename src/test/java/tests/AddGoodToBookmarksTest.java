package tests;

import elements.GoodElement;
import elements.ItemElement;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.GoodsPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddGoodToBookmarksTest extends BaseTest {

    @Tag("bookmark")
    @Timeout(value = 1, unit = TimeUnit.MINUTES)
    @DisplayName("correct addition and display to bookmarks")
    @Test
    public void additionalBookmarks() throws IOException {

        GoodsPage goodsPage = new LoginPage(driver).get()
                .login()
                .openGoodsPage();

        GoodElement goodsElement = goodsPage.getGoodElementFromList(0);

        String nameGoods = goodsElement.getName();
        String priceGoods = goodsElement.getPrice();
        System.out.println(priceGoods + " - " + nameGoods);

        goodsElement.openGoodsLink()
                .addBookmark()
                .close();
        new Actions(driver).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).build().perform();
        goodsPage.get()
                .openBasket()
                .openBookmarks();

        ItemElement itemElement = goodsPage.getGoodElementFromBasket(0);
        String nameItem = itemElement.getName();
        String priceItem = itemElement.getPrice();
        System.out.println(priceItem + " - " + nameItem);

        Assertions.assertAll("Matching of products selected on the main page and added to bookmarks",
                () -> assertThat(nameGoods)
                        .as("проверка идентичности имени")
                        .isEqualTo(nameItem),
                () -> assertThat(priceGoods)
                        .as("проверка идентичности цены товара")
                        .isEqualTo(priceItem));

        goodsPage.deleteBookmark();
    }

}
