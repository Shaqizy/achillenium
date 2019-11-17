package pages.forms;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BaseEF extends AkitaPage {

    public static ElementsCollection getElementsFromDropdown(){
        return $$("[data-component='OptionsItem']");
    }

    public static SelenideElement getLoader(){
        return $("[data-component='Loader']");
    }

}
