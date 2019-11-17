package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import pages.forms.BaseEF;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
import ru.alfabank.tests.core.helpers.PropertyLoader;


/**
 * В этом классе описываются сущности, которые будут использоваться в большом кол-ве степов
 */

@Slf4j
class BaseMethods {

    AkitaScenario currentScenario = AkitaScenario.getInstance();
    AkitaPage currentPage;
    private final String CONDITION_TIMEOUT_PARAM_NAME = "elementConditionTimeout";
    private final String OTP_PASSWORD_PARAM_NAME = "defaultOtpPassword";
    final long CONDITION_TIMEOUT = Long.parseLong(PropertyLoader.getPropertyOrValue(CONDITION_TIMEOUT_PARAM_NAME));
    final String OTP_PASSWORD = PropertyLoader.getPropertyOrValue(OTP_PASSWORD_PARAM_NAME);

    /**
     * Метод для заполнения input в IE, на текущий момент заполнять поля быстро не выходит, приходится бить строку на символы и вводить значения посимвольно
     * @param element элемент
     * @param expectedText ожидаемый текст
     */
    void sitIeVal(SelenideElement element, String expectedText) {
        element.scrollIntoView(false);
        element.click();
        for (char c : expectedText.toCharArray()) {
            element.sendKeys("" + c);
            BaseEF.getLoader().waitUntil(Condition.not(Condition.visible), CONDITION_TIMEOUT);
            try {
                if (BaseEF.getElementsFromDropdown().size() == 1) {
                    BaseEF.getElementsFromDropdown().first().click();
                    element.pressTab();
                    break;
                }
            } catch (IndexOutOfBoundsException e){
                log.info("В выпадающем списке отсутствуют элементы на которые можно кликнуть");
            }
        }
    }
}