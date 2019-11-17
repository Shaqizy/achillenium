package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import cucumber.api.java.ru.И;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import pages.forms.BaseEF;
import ru.alfabank.steps.ElementsInteractionSteps;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class DropDownSteps extends BaseMethods {

    @И("^из выпадающего списка \"([^\"]*)\" выбран элемент с текстом \"(.*)\"$")
    public void selectElementFromDropDownListWithText(String elementName, String expectedText) {
        currentPage = currentScenario.getCurrentPage();
        currentPage.getElement(elementName).scrollIntoView(false).click();
        BaseEF.getElementsFromDropdown().shouldBe(CollectionCondition.sizeGreaterThan(0), CONDITION_TIMEOUT).filter(Condition.text(expectedText)).first().click();
    }

    @И("^из выпадающего списка \"([^\"]*)\" сделан клик на запись с текстом \"(.*)\"$")
    public void clickOnRowWithTextInBox(String elementName, String expectedText) {
        currentPage = currentScenario.getCurrentPage();
        currentPage.getElement(elementName).scrollIntoView(false).click();
        ElementsInteractionSteps elementsInteractionSteps = new ElementsInteractionSteps();
        $(By.xpath(elementsInteractionSteps.getTranslateNormalizeSpaceText(elementsInteractionSteps.getPropertyOrStringVariableOrValue(expectedText)))).click();
    }

}
