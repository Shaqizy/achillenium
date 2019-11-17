package pages.auth;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.annotations.Optional;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Страница логина")
public class LoginPage extends AkitaPage {

    @FindBy(className = "passp-auth-header")
    @Name("Заголовок страницы логина")
    public SelenideElement header;

    @FindBy(name = "login")
    @Name("Логин")
    public SelenideElement login;

    @FindBy(className = "passp-form-field__link")
    @Name("Забыли логин?")
    public SelenideElement forgotLogin;

    @FindBy(className = "passp-sign-in-button")
    @Name("Войти")
    public SelenideElement signButton;

    @Optional
    @FindBy(className = "passp-exp-register-button")
    @Name("Зарегистрироваться")
    public SelenideElement registerButton;

    @Optional
    @FindBy(className = "passp-current-account__display-name")
    @Name("Текущий пользователь")
    public SelenideElement currentUser;

    @Optional
    @FindBy(name = "passwd")
    @Name("Пароль")
    public SelenideElement password;

    @Optional
    @FindBy(className = "passp-form-field__link")
    @Name("Забыли пароль?")
    public SelenideElement forgotPassword;
}
