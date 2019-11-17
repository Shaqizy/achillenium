package steps;

import com.codeborne.selenide.Condition;
import cucumber.api.java.ru.И;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrouppedSteps extends BaseMethods {
    @И("^пользователь залогинился в почту с логином \"([^\"]*)\" и паролем \"([^\"]*)\"$")
    public void logIn(String login, String pass) {
        currentPage = currentScenario.getCurrentPage();
        currentPage.getElement("Логин").setValue(login);
        currentPage.getElement("Пароль").setValue(pass);
        currentPage.getElement("Войти").click();
    }

    @И("^выполнена обработка ОТП подписи$")
    public void sign() {
        currentPage = currentScenario.getPage("Подписание документов");
        // Для IE не всегда хватает таймаута, поэтому воткнул дополнительный
        currentPage.getElement("SMS код").waitUntil(Condition.visible, CONDITION_TIMEOUT).setValue(OTP_PASSWORD);
        currentPage.getElement("Подтвердить").click();
        currentPage.getElement("Модальное окно").waitUntil(Condition.not(Condition.visible), CONDITION_TIMEOUT);
    }
}
