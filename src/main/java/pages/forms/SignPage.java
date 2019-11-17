package pages.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.annotations.Optional;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Подписание документов")
public class SignPage extends AkitaPage {

    /*
     * Блок: Общая информация
     */

    @Name("Предупреждение")
    @FindBy(xpath = "//div[contains(@class, 'ModalContent')]//div[contains(@class, 'Text')]")
    @Optional
    public SelenideElement warning;

    @Name("Номер документа")
    @FindBy(xpath = "//*[text() = 'Номер документа']/../div[2]")
    public SelenideElement docNumber;

    @Name("Дата документа")
    @FindBy(xpath = "//*[text() = 'Дата документа']/../div[2]")
    public SelenideElement docDate;

    @Name("Счет плательщика")
    @FindBy(xpath = "//*[text() = 'Счет плательщика']/../div[2]")
    public SelenideElement payerAccount;

    @Name("Счет получателя")
    @FindBy(xpath = "//*[text() = 'Счет получателя']/../div[2]")
    public SelenideElement receiverAccount;

    /*
     * Блок: Способ подписи
     */

    @Name("Подписать")
    @FindBy(id = "SignButton")
    @Optional
    public SelenideElement signButton;

    @Name("SMS код")
    @FindBy(id = "SmsCode")
    @Optional
    public SelenideElement smsCode;

    @Name("Подтвердить")
    @FindBy(id = "SmsSubmit")
    @Optional
    public SelenideElement SmsSubmit;

    @Name("Модальное окно")
    @FindBy(css = "[data-component='ModalWindow']")
    public SelenideElement mainWindow;

}
