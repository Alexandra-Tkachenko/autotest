package tests;

import elements.Criteria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.GoodsPage;
import pages.LoginPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Search Tests")
public class SearchResults extends BaseTest {

    @Tag("search")
    @Timeout(value = 1, unit = TimeUnit.MINUTES)
    @DisplayName("correct search by query")
    @ParameterizedTest
    @ValueSource(strings = {"кружка"})
    public void searchByQuery(String search) throws IOException {
        assertThat(new LoginPage(BaseTest.driver).get().login().openGoodsPage().writeSearchQuery(search)
                .getGoodElementFromList(0).getName()).isNotNull().containsIgnoringCase(search);
    }

    @Nested
    @DisplayName("Price tests")
    class setPrice {

        static Stream<Arguments> stringsListProvider() {
            return Stream.of(Arguments.arguments("чай", "100", "500"));
        }

        @Tag("search")
        @Tag("price")
        @Timeout(value = 2, unit = TimeUnit.MINUTES)
        @DisplayName("correct search in the price range")
        @ParameterizedTest
        @MethodSource("stringsListProvider")
        public void searchInPriceRange(String search, String min, String max) throws IOException {
            GoodsPage goodsPage = new LoginPage(BaseTest.driver).get().login().openGoodsPage().writeSearchQuery(search);

            assertThat(goodsPage.setMinPrice(min).setMaxPrice(max)
                    .checkPrice(Integer.parseInt(min), Integer.parseInt(max)))
                    .as("Цена не в диапазоне").isTrue();
        }

    }

    @Nested
    @DisplayName("Sort tests")
    class setSort {

        @Tag("sort")
        @Tag("search")
        @Timeout(value = 1, unit = TimeUnit.MINUTES)
        @DisplayName("correct search according to sorting")
        @ParameterizedTest
        @ValueSource(strings = {"кофе"})
        public void searchByCriterion(String search) throws IOException {
            GoodsPage goodsPage = new LoginPage(BaseTest.driver).get().login().openGoodsPage().writeSearchQuery(search);

            assertThat(goodsPage.chooseSort(Criteria.CHEAP).isCheapSort())
                    .as("Cортировка не %s", Criteria.CHEAP.toString()).isTrue();
        }


    }
}
