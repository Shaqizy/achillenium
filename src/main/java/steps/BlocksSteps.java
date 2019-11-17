package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.ru.Когда;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlocksSteps extends BaseMethods {

    @Когда("^выполнено нажатие на элемент \"([^\"]*)\" в блоке \"([^\"]*)\"$")
    public void clickOnElementFromBlock(String elementName, String blockName) {
        currentScenario.getPage(blockName).getElement(elementName).waitUntil(Condition.visible, CONDITION_TIMEOUT).click();
    }

    @Когда("^ждем исчезновение элемента \"([^\"]*)\" в блоке \"([^\"]*)\"$")
    public void waitElemDisapperedInBlock(String elementName, String blockName) {
        currentScenario.getPage(blockName).getElement(elementName).waitUntil(Condition.disappears, CONDITION_TIMEOUT);
    }

    @Когда("^выбран системный фильтр со значением \"([^\"]*)\"$")
    public void selectSystemFilter(String textOfOptionItem) {
        currentScenario.getPage("Фильтр")
                .getElement("Выпадающий список фильтра").waitUntil(Condition.visible, CONDITION_TIMEOUT).click();

        currentScenario.getPage("Фильтр")
                .getElementsList("Опции системного фильтра").stream()
                .filter(i -> i.waitUntil(Condition.visible, CONDITION_TIMEOUT).getText().equals(textOfOptionItem))
                .forEach(SelenideElement::click);
    }

}
