package pages.forms;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.annotations.Optional;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@Name("Платежное поручение")
public class PaymentsPage extends AkitaPage {

    @Name("Статус")
    @FindBy(xpath = "//*[@data-component = 'Status']")
    public SelenideElement status;

    /*
     * Блок: Сумма и назначение платежа
     */

    @Name("Сумма")
    @FindBy(id = "amount")
    public SelenideElement amount;

    @Name("Назначение платежа")
    @FindBy(id = "paymentPurpose")
    public SelenideElement paymentPurpose;

    @Name("Способ расчета НДС")
    @FindBy(id = "vatCalculationRule")
    public SelenideElement vatCalculationRule;

    @Name("Процент НДС")
    @FindBy(id = "vatRate")
    public SelenideElement vatRate;

    @Name("Сумма НДС")
    @FindBy(id = "vatSum")
    public SelenideElement vatSum;

    @Name("Код вида валютной операции")
    @FindBy(id = "currencyOperationType")
    public SelenideElement currencyOperationType;

    @Name("без НДС")
    @FindBy(xpath = "//*[text() = 'без НДС']")
    @Optional
    public SelenideElement withoutNDS;

    @Name("НДС 20%")
    @FindBy(xpath = "//*[text() = 'НДС 20%']")
    @Optional
    public SelenideElement NDS20;

    @Name("НДС 18%")
    @FindBy(xpath = "//*[text() = 'НДС 18%']")
    @Optional
    public SelenideElement NDS18;

    @Name("НДС 10%")
    @FindBy(xpath = "//*[text() = 'НДС 10%']")
    @Optional
    public SelenideElement NDS10;

    /*
     * Блок: Плательщик
     */

    @Name("Счет плательщика")
    @FindBy(xpath = "//*[contains(@id, 'ayerAccount')]")
    public SelenideElement payerAccount;

    @Name("ИНН плательщика")
    @FindBy(id = "payerINN")
    @Optional
    public SelenideElement payerINN;

    @Name("КПП плателищка")
    @FindBy(id = "payerKPP")
    @Optional
    public SelenideElement payerKPP;

    @Name("Наименование плательщика")
    @FindBy(id = "payerName")
    @Optional
    public SelenideElement payerName;

    @Name("БИК плательщика")
    @FindBy(id = "payerBankBIC")
    @Optional
    public SelenideElement payerBankBIC;

    @Name("Кор. счет плательщика")
    @FindBy(id = "payerBankCorrAccount")
    @Optional
    public SelenideElement payerBankCorrAccount;

    @Name("Банк плательщика")
    @FindBy(id = "payerBankName")
    @Optional
    public SelenideElement payerBankName;

    /*
     * Блок: Получатель
     */

    @Name("Счет получателя")
    @FindBy(xpath = "//*[contains(@id, 'eceiverAccount')]")
    public SelenideElement receiverAccount;

    @Name("ИНН получателя")
    @FindBy(id = "receiverINN")
    @Optional
    public SelenideElement receiverINN;

    @Name("КПП получателя")
    @FindBy(id = "receiverKPP")
    @Optional
    public SelenideElement receiverKPP;

    @Name("Наименование получателя")
    @FindBy(id = "receiverName")
    @Optional
    public SelenideElement receiverName;

    @Name("БИК получателя")
    @FindBy(id = "receiverBankBIC")
    @Optional
    public SelenideElement receiverBankBIC;

    @Name("Кор. счет получателя")
    @FindBy(id = "receiverBankCorrAccount")
    @Optional
    public SelenideElement receiverBankCorrAccount;

    @Name("Банк получателя")
    @FindBy(id = "receiverBankName")
    @Optional
    public SelenideElement receiverBankName;

    @Name("УИН")
    @FindBy(id = "uip")
    @Optional
    public SelenideElement uip;

    /*
     * Блок: Общая информация
     */

    @Name("Номер")
    @FindBy(id = "docNumber")
    public SelenideElement docNumber;

    @Name("Дата")
    @FindBy(id = "docDate")
    public SelenideElement docDate;

    @Name("Очер. платежа")
    @FindBy(id = "paymentPriority")
    @Optional
    public SelenideElement paymentPriority;

    @Name("Вид операции")
    @FindBy(id = "operationType")
    @Optional
    public SelenideElement operationType;

    @Name("Вид платежа")
    @FindBy(id = "paymentKind")
    @Optional
    public SelenideElement paymentKind;

    @Name("Платеж в бюджет")
    @FindBy(xpath = "//*[text() = 'Платеж в бюджет']")
    @Optional
    public SelenideElement isBudget;

    /*
     * Блок: Платеж в бюджет
     */

    @Name("101 Пок. статуса")
    @FindBy(id = "drawerStatus")
    @Optional
    public SelenideElement drawerStatus;

    @Name("104 КБК")
    @FindBy(id = "cbc")
    @Optional
    public SelenideElement cbc;

    @Name("105 ОКТМО")
    @FindBy(id = "ocato")
    @Optional
    public SelenideElement ocato;

    @Name("106 Пок. основания")
    @FindBy(id = "payReason")
    @Optional
    public SelenideElement payReason;

    @Name("108 Пок. номера")
    @FindBy(id = "taxDocNumber")
    @Optional
    public SelenideElement taxDocNumber;

    @Name("109 Пок. даты докум - День")
    @FindBy(id = "docDateDay")
    @Optional
    public SelenideElement docDateDay;

    @Name("109 Пок. даты докум - Месяц")
    @FindBy(id = "docDateMonth")
    @Optional
    public SelenideElement docDateMonth;

    @Name("109 Пок. даты докум - Год")
    @FindBy(id = "docDateYear")
    @Optional
    public SelenideElement docDateYear;

    @Name("110 Пок. типа")
    @FindBy(id = "chargeType")
    @Optional
    public SelenideElement chargeType;

    @Name("107 Налог. период / Код тамож. органа")
    @FindBy(id = "taxOrCustoms")
    @Optional
    public SelenideElement taxOrCustoms;

    @Name("107 Налог. период - День")
    @FindBy(id = "taxPeriodDay1")
    @Optional
    public SelenideElement taxPeriodDay1;

    @Name("107 Налог. период - Месяц")
    @FindBy(id = "taxPeriodMonth")
    @Optional
    public SelenideElement taxPeriodMonth;

    @Name("107 Налог. период - Год")
    @FindBy(id = "taxPeriodYear")
    @Optional
    public SelenideElement taxPeriodYear;

    @Name("107 Код тамож. органа")
    @FindBy(id = "taxPeriodDay2")
    @Optional
    public SelenideElement taxPeriodDay2;

}
