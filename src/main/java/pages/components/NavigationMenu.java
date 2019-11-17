package pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Навигация")
public class NavigationMenu extends AkitaPage {

    @FindBy(id = "main")
    @Name("Главная")
    public SelenideElement main;

    @FindBy(id = "statements")
    @Name("Выписки")
    public SelenideElement statements;

    @FindBy(id = "messages")
    @Name("Письма")
    public SelenideElement messages;

    @FindBy(id = "rPayments")
    @Name("Рублевые платежи")
    public SelenideElement payments;

    @FindBy(id = "products")
    @Name("Продукты")
    public SelenideElement products;

    @FindBy(id = "tasks")
    @Name("Журнал импорта")
    public SelenideElement tasks;

    @FindBy(id = "settings")
    @Name("Настройки")
    public SelenideElement settings;

    @FindBy(id = "support")
    @Name("Помощь")
    public SelenideElement help;

}
