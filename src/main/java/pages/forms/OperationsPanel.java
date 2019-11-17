package pages.forms;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.annotations.Optional;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

@FindBy(css = "[data-component='OperationPanel']")
@Name("Панель операций")
public class OperationsPanel extends AkitaPage {

    @FindBy(css = "[data-id='back']")
    @Name("Назад")
    public SelenideElement backButton;

    @FindBy(xpath = "//*[@data-id='save']")
    @Name("Сохранить")
    public SelenideElement saveButton;

    @FindBy(xpath = "//*[@data-id='signAndSend']")
    @Name("Подписать и отправить")
    public SelenideElement signPushButton;

    @Optional
    @FindBy(xpath = "//*[@data-id='recall']")
    @Name("Отозвать")
    public SelenideElement recall;

    @Optional
    @FindBy(css = "[data-id='sign']")
    @Name("Подписать")
    public SelenideElement signButton;

    @Optional
    @FindBy(css = "[data-id='send']")
    @Name("Отправить")
    public SelenideElement sendButton;

    @Optional
    @FindBy(css = "[data-id='repeat']")
    @Name("Копировать")
    public SelenideElement copyButton;

    @Optional
    @FindBy(css = "[data-id='print']")
    @Name("Скачать")
    public SelenideElement downloadButton;

    @Optional
    @FindBy(css = "[data-type='arrow-down-filled']")
    @Name("Кнопка форматов для скачивания")
    public SelenideElement downloadFormatButton;

    @Optional
    @FindBy(css = "[data-id='pdf']")
    @Name("PDF")
    public SelenideElement pdfFormatButton;

    @Optional
    @FindBy(css = "[data-id='rtf']")
    @Name("RTF")
    public SelenideElement rtfFormatButton;

    @Optional
    @FindBy(css = "[data-id='xls']")
    @Name("XLS")
    public SelenideElement xlsFormatButton;

    @Optional
    @FindBy(css = "[data-id='OperationPanelExtraOpener']")
    @Name("Еще")
    public SelenideElement moreOptionsButton;

    @Optional
    @FindBy(css = "[data-id='remove']")
    @Name("Удалить")
    public SelenideElement removeButton;

    @Optional
    @FindBy(css = "[data-id='history']")
    @Name("История статусов")
    public SelenideElement historyButton;

    @Optional
    @FindBy(css = "[data-id='archive']")
    @Name("Переместить в архив")
    public SelenideElement archiveButton;

    @Optional
    @FindBy(css = "[data-id='visa']")
    @Name("Виза")
    public SelenideElement visaButton;

    @Name("Нотификация")
    @FindBy(xpath = "//*[@id = 'Notification']//*[contains(@class, 'Title')]")
    @Optional
    public SelenideElement notification;

}
