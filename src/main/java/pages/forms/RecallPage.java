package pages.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Отзыв")
public class RecallPage extends AkitaPage {

    @Name("Статус")
    @FindBy(xpath = "//*[@data-component = 'Status']")
    public SelenideElement status;

    @Name("Организация")
    @FindBy(xpath = "//*[text() = 'Организация']/../td[2]")
    public SelenideElement organization;

    @Name("ИНН")
    @FindBy(xpath = "//*[text() = 'ИНН']/../td[2]")
    public SelenideElement inn;

    @Name("Причина отзыва")
    @FindBy(id = "reason")
    public SelenideElement reason;

}
