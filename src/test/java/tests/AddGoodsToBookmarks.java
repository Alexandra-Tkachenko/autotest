package tests;

import elements.GoodElement;
import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.GoodsPage;
import pages.LoginPage;

import java.io.IOException;


class AddGoodToBookmarks extends BaseTest {

    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    public void additionalBookmarks() throws IOException {

        GoodsPage goodsPage = new LoginPage(driver).get().login().openGoodsPage();

        GoodElement goodsElement = goodsPage.getGoodElement(0).get();

        String nameGoods = goodsElement.getNameGood();
        String priceGoods = goodsElement.getPriceGood();
        System.out.println(priceGoods + " - " + nameGoods);

        goodsElement.openGoodsLink().addBookmark().close();
        new Actions(driver).sendKeys(Keys.PAGE_UP).build().perform(); //
        goodsPage.get().openBasket().openBookmarks();
//
//            ItemElement itemElement = goodsPage.getItems().get(0);
//            String nameItem = itemElement.getName();
//            String priceItem = itemElement.getPrice();
//            //System.out.println(priceItem + " - " + nameItem);
//
//            Assertions.assertAll("Matching of products selected on the main page and added to bookmarks",
//                    () -> Assertions.assertEquals(nameGoods, nameItem, "Имена не совпали"),
//                    () -> Assertions.assertEquals(priceGoods, priceItem, "Цены не совпали"));
//
//            goodsPage.deleteBookmark();

    }

}
