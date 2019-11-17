package pages.components;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.annotations.Optional;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import java.util.List;

@Name("Фильтр")
public class ScrollerFilter extends AkitaPage {

    @Name("Выпадающий список фильтра")
    @FindBy(id = "filters-dropdown-box")
    public SelenideElement openSystemFilterOptions;

    @Name("Опции системного фильтра")
    @FindBy(className = "b-form-compact__popup-option")
    @Optional
    public List<SelenideElement> filterOptionsItem;


}
