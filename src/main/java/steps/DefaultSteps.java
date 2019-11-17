package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import cucumber.api.java.ru.И;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.IE;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getSelenideDriver;
import static ru.alfabank.alfatest.cucumber.ScopedVariables.resolveVars;
import static ru.alfabank.alfatest.cucumber.api.AkitaScenario.sleep;
import static ru.alfabank.tests.core.drivers.CustomDriverProvider.BROWSER;
import static ru.alfabank.tests.core.helpers.PropertyLoader.*;

/**
 * В данном методе хранятся шаги, которые нельзя объеденить по каким-либо признакам
 */

@Slf4j
public class DefaultSteps extends BaseMethods{
    private static final String OVERRIDE_LINK_SELECTOR = "#overridelink";


    @И("^осуществлён переход на страницу \"([^\"]*)\" по ссылке \"([^\"]*)\"$")
    public void goToSelectedPageByLink(String pageName, String urlOrName) {
        String address = loadProperty(urlOrName, resolveVars(urlOrName));
        currentScenario.write(" url = " + address);
        if (loadSystemPropertyOrDefault(BROWSER, CHROME).equalsIgnoreCase(IE)) {
            open(address);
            // TODO: костыль, придумать как лучше
            int counter = 0;
            while (true) {
                if (counter >= 5) {
                    break;
                }
                if ($(OVERRIDE_LINK_SELECTOR).is(visible)) {
                    $(OVERRIDE_LINK_SELECTOR).click();
                    break;
                } else {
                    sleep(1);
                    counter++;
                }
            }
        } else {
            open(address);
        }
        currentScenario.setCurrentPage(currentScenario.getPage(pageName));
    }


    @И("^страница прокручена и элемент \"([^\"]*)\" внизу страницы$")
    public void scrollToElement(String elementName) {
        currentScenario.getCurrentPage().getElement(elementName).scrollIntoView(false);
    }

    @И("^элемент \"([^\"]*)\" отобразился на странице$")
    public void showElementOnPage(String elementName) {
        currentScenario.getCurrentPage().getElement(elementName).isDisplayed();
    }

    @И("^зависимый от обновления элемент \"([^\"]*)\" содержит текст \"([^\"]*)\"$")
    public void waitElementState(String elementName, String expectedText) {
        currentPage = currentScenario.getCurrentPage();
        expectedText = !getPropertyOrValue(expectedText).isEmpty() ? getPropertyOrValue(expectedText) : expectedText;
        int counter = 0;
        while (!currentPage.getElement(elementName).waitUntil(Condition.visible, CONDITION_TIMEOUT).getText().toLowerCase().contains(expectedText.toLowerCase())) {
            getSelenideDriver().refresh();
            sleep(1);
            if (counter >= 5) {
                throw new AssertionError(
                        String.format("Элемент %s не содержит значение %s. Текущее значение элемента %s",
                                elementName,
                                expectedText,
                                currentPage.getElement(elementName).getText()),
                        new Throwable());
            }
            counter++;
        }
    }


    @И("^страница прокручена вверх$")
    public void scrollToUp() {
        Selenide.executeJavaScript("window.scrollTo(0, 0)");
    }
}