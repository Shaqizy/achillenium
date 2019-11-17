package steps;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import pages.forms.BaseEF;

import static com.codeborne.selenide.WebDriverRunner.isIE;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class FillFieldsSteps extends BaseMethods {


    @И("^в поле \"([^\"]*)\" сделан клик и введено \"(.*)\"$")
    public void clickAndFillField(String elementName, String value) {
        SelenideElement field = currentScenario.getCurrentPage().getElement(elementName);
        field.click();
        field.setValue(value).pressEnter();
    }

    @И("^в поле \"([^\"]*)\" введено \"(.*)\"$")
    public void setFillField(String elementName, String value) {
        if (isIE()) {
            sitIeVal(currentScenario.getCurrentPage().getElement(elementName), value);
        } else {
            currentScenario.getCurrentPage().getElement(elementName).setValue(value);
        }
    }

    @И("^в поле \"([^\"]*)\" введено значение из переменной \"(.*)\"$")
    public void setFieldValueFromVar(String elementName, String variableName) {
        if (isIE()) {
            sitIeVal(currentScenario.getCurrentPage().getElement(elementName), currentScenario.getVar(variableName).toString());
        } else {
            currentScenario.getCurrentPage().getElement(elementName).setValue(currentScenario.getVar(variableName).toString());
        }
    }

    @Когда("^в поле \"([^\"]*)\" введено случайное число из (\\d+) (?:цифр|цифры) начинающееся не с (\\d+)$")
    public void inputRandomNumSequence(String elementName, int seqLength, int exclude) {
        SelenideElement valueInput = currentScenario.getCurrentPage().getElement(elementName);
        valueInput.clear();
        String numSeq = RandomStringUtils.randomNumeric(seqLength);
        while (Integer.parseInt(String.valueOf(numSeq.charAt(0))) == exclude) {
            numSeq = RandomStringUtils.randomNumeric(seqLength);
        }
        valueInput.setValue(numSeq);
        currentScenario.write(String.format("В поле [%s] введено [%s]", elementName, numSeq));
    }

    @И("^поле \"([^\"]*)\" не пустое$")
    public void setValueForAutocompleteField(String elementName) {
        currentPage = currentScenario.getCurrentPage();
        currentPage.getElement(elementName).shouldBe(Condition.not(Condition.empty));
    }

    @И("^поле с автокомплитом \"([^\"]*)\" заполнено значением \"(.*)\"$")
    public void setValueForAutocompleteField(String elementName, String expectedText) {
        currentPage = currentScenario.getCurrentPage();
        SelenideElement element = currentPage.getElement(elementName);
        if (isIE()) {
            sitIeVal(element, expectedText);
        } else {
            element.setValue(expectedText);
            BaseEF.getElementsFromDropdown().shouldHave(CollectionCondition.size(1)).get(0).click();
        }
        String actualText = element.getValue();
        if (actualText.contains(".")) {
            actualText = actualText.replace(".", "");
        }
        assertThat(
                String.format("Поле с автокомплитом %s имеет значение %s, а должно иметь значение %s",
                        element, actualText, expectedText),
                actualText.equalsIgnoreCase(expectedText)
        );
    }


}