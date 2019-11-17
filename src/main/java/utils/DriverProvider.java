package utils;

import com.codeborne.selenide.WebDriverProvider;
import io.github.bonigarcia.wdm.Architecture;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Browsers.*;
import static ru.alfabank.tests.core.drivers.CustomDriverProvider.*;
import static ru.alfabank.tests.core.helpers.PropertyLoader.loadSystemPropertyOrDefault;

@Slf4j
public class DriverProvider implements WebDriverProvider {
    private String[] options = loadSystemPropertyOrDefault("options", "").split(" ");
    private String teamName = loadSystemPropertyOrDefault("teamName","");

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        String expectedBrowser = loadSystemPropertyOrDefault(BROWSER, CHROME);
        String remoteUrl = loadSystemPropertyOrDefault(REMOTE_URL, LOCAL);

        if (FIREFOX.equalsIgnoreCase(expectedBrowser)) {
            return LOCAL.equalsIgnoreCase(remoteUrl) ? createFirefoxDriver(capabilities) : getRemoteDriver(getFirefoxDriverOptions(capabilities), remoteUrl);
        }

        if (IE.equalsIgnoreCase(expectedBrowser)) {
            return LOCAL.equalsIgnoreCase(remoteUrl) ? createIEDriver(capabilities) : getRemoteDriver(getIEDriverOptions(capabilities), remoteUrl);
        }

        if (MOBILE_DRIVER.equalsIgnoreCase(expectedBrowser)) {
            return LOCAL.equalsIgnoreCase(remoteUrl) ? new ChromeDriver(getMobileChromeOptions(capabilities)) : getRemoteDriver(getMobileChromeOptions(capabilities), remoteUrl);
        }

        if (SAFARI.equalsIgnoreCase(expectedBrowser)) {
            return LOCAL.equalsIgnoreCase(remoteUrl) ? createSafariDriver(capabilities) : getRemoteDriver(getSafariDriverOptions(capabilities), remoteUrl);
        }

        log.info("remoteUrl=" + remoteUrl + " expectedBrowser= " + expectedBrowser + " BROWSER_VERSION=" + System.getProperty(CapabilityType.BROWSER_VERSION));
        return LOCAL.equalsIgnoreCase(remoteUrl) ? createChromeDriver(capabilities) : getRemoteDriver(getChromeDriverOptions(capabilities), remoteUrl);
    }

    private WebDriver getRemoteDriver(MutableCapabilities capabilities, String remoteUrl) {
        log.info("---------------Remote Driver---------------------");
        Boolean isSelenoidRun = loadSystemPropertyOrDefault(SELENOID, true);
        if (isSelenoidRun) {
            if (!teamName.isEmpty()) {
                capabilities.setCapability("name", teamName);
            }
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("screenResolution", String.format("%sx%s", loadSystemPropertyOrDefault(WINDOW_WIDTH, DEFAULT_WIDTH),
                    loadSystemPropertyOrDefault(WINDOW_HEIGHT, DEFAULT_HEIGHT)));
        }
        try {
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(
                    URI.create(remoteUrl).toURL(),
                    capabilities
            );
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            return remoteWebDriver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private WebDriver createChromeDriver(DesiredCapabilities capabilities) {
        if (!capabilities.getVersion().isEmpty()) {
            WebDriverManager.chromedriver().version(capabilities.getVersion()).setup();
        } else {
            WebDriverManager.chromedriver().setup();
        }
        ChromeDriver chromeDriver = new ChromeDriver(getChromeDriverOptions(capabilities));
//        EventFiringWebDriver eventDriver = new EventFiringWebDriver(chromeDriver);
//        eventDriver.register(new EventHandler());
        chromeDriver.manage().window().setSize(setDimension());
        return chromeDriver;
    }

    private ChromeOptions getChromeDriverOptions(DesiredCapabilities capabilities) {
        log.info("---------------Chrome Driver---------------------");
        ChromeOptions chromeOptions = !options[0].equals("") ? new ChromeOptions().addArguments(options) : new ChromeOptions();
        chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, loadSystemPropertyOrDefault(CapabilityType.BROWSER_VERSION, VERSION_LATEST));
        chromeOptions.merge(capabilities);
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        return chromeOptions;
    }

    private WebDriver createFirefoxDriver(DesiredCapabilities capabilities) {
        if (!capabilities.getVersion().isEmpty()) {
            WebDriverManager.firefoxdriver().version(capabilities.getVersion()).setup();
        } else {
            WebDriverManager.firefoxdriver().architecture(Architecture.X32).setup();
        }
        FirefoxDriver firefoxDriver = new FirefoxDriver(getFirefoxDriverOptions(capabilities));
        firefoxDriver.manage().window().setSize(setDimension());
        return firefoxDriver;
    }

    private FirefoxOptions getFirefoxDriverOptions(DesiredCapabilities capabilities) {
        log.info("---------------Firefox Driver---------------------");
        FirefoxOptions firefoxOptions = !options[0].equals("") ? new FirefoxOptions().addArguments(options) : new FirefoxOptions();
        capabilities.setVersion(loadSystemPropertyOrDefault(CapabilityType.BROWSER_VERSION, VERSION_LATEST));
        firefoxOptions.merge(capabilities);
        return firefoxOptions;
    }


    private WebDriver createIEDriver(DesiredCapabilities capabilities) {
        if (!capabilities.getVersion().isEmpty()) {
            WebDriverManager.iedriver().architecture(Architecture.X32).version(capabilities.getVersion()).setup();
        } else {
            WebDriverManager.iedriver().architecture(Architecture.X32).setup();
        }
        InternetExplorerDriver internetExplorerDriver = new InternetExplorerDriver(getIEDriverOptions(capabilities));
        internetExplorerDriver.manage().window().setSize(setDimension());
        return internetExplorerDriver;
    }

    private InternetExplorerOptions getIEDriverOptions(DesiredCapabilities capabilities) {
        log.info("---------------IE Driver---------------------");
        InternetExplorerOptions internetExplorerOptions = !options[0].equals("") ? new InternetExplorerOptions().addCommandSwitches(options) : new InternetExplorerOptions();
        internetExplorerOptions.setCapability(CapabilityType.BROWSER_VERSION, loadSystemPropertyOrDefault(CapabilityType.BROWSER_VERSION, VERSION_LATEST));
        internetExplorerOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        internetExplorerOptions.setCapability("ie.usePerProcessProxy", "true");
        internetExplorerOptions.setCapability("requireWindowFocus", "false");
        internetExplorerOptions.setCapability("ie.browserCommandLineSwitches", "-private");
        internetExplorerOptions.setCapability("ie.ensureCleanSession", "true");
        internetExplorerOptions.merge(capabilities);
        return internetExplorerOptions;
    }

    private WebDriver createSafariDriver(DesiredCapabilities capabilities) {
        return null;
    }

    private FirefoxOptions getSafariDriverOptions(DesiredCapabilities capabilities) {
        return null;
    }

    private FirefoxOptions getMobileChromeOptions(DesiredCapabilities capabilities) {
        return null;
    }

    private Dimension setDimension() {
        return new Dimension(loadSystemPropertyOrDefault(WINDOW_WIDTH, DEFAULT_WIDTH),
                loadSystemPropertyOrDefault(WINDOW_HEIGHT, DEFAULT_HEIGHT));
    }
}
