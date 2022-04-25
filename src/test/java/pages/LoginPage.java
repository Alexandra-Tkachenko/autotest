package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import elements.User;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class LoginPage extends BasePage<LoginPage> {
    private static final By LOGIN = By.id("field_email");
    private static final By PASSWORD = By.id("field_password");
    private static final By SUBMIT = By.xpath("//*[contains(@value, 'Войти')]");

    private final static String URL = BASE_URL;

    private static User user;

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
        user = parseUserData();
    }

    public MainPage login() {
        clear(LOGIN);
        sendKeys(LOGIN, user.getLogin());

        clear(PASSWORD);
        sendKeys(PASSWORD, user.getPassword());

        click(SUBMIT);
        return new MainPage(driver).get();
    }

    private static User parseUserData() throws IOException {
        return (new ObjectMapper()).readValue(new File("resources" + File.separator + "source.json"), User.class);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        Assertions.assertEquals(URL, driver.getCurrentUrl(), "Проблема с загрузкой страницы");
        assertionDisplayed(LOGIN, "Поля логина не загрузилось");
        assertionDisplayed(PASSWORD, "Поля пароля не загрузилось");
        assertionDisplayed(SUBMIT, "Кнопка 'войти' не загрузилaсь");
    }
}
