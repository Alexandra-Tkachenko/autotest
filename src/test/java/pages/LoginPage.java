package pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class LoginPage extends BasePage {
    private static final By LOGIN = By.id("field_email");           //By.xpath("//*[@id='field_email']");
    private static final By PASSWORD = By.id("field_password");     //By.xpath("//*[@id='field_password']");
    private static final By SUBMIT = By.xpath("//*[contains(@value, 'Войти')]");
    private static User user;

    public LoginPage(WebDriver driver) throws IOException {
        super(driver);
        user = parseUserData();
    }

    public UserPage login() {
        clear(LOGIN);
        sendKeys(LOGIN, user.getLogin());

        clear(PASSWORD);
        sendKeys(PASSWORD, user.getPassword());

        click(SUBMIT);
        return new UserPage(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        assertion(LOGIN, WAIT_TIME, "Поля логина не загрузилось");
        assertion(PASSWORD, WAIT_TIME, "Поля пароля не загрузилось");
        assertion(SUBMIT, WAIT_TIME, "Кнопка 'войти' не загрузилaсь");
    }

    private static User parseUserData() throws IOException {
        return (new ObjectMapper()).readValue(new File("src/test/java/resources/source.json"), User.class);
    }

    @Getter
    static class User {
        private String login;
        private String password;
    }
}
