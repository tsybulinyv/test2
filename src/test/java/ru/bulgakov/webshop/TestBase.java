package ru.bulgakov.webshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;
import org.openqa.selenium.UsernameAndPassword;
import ru.bulgakov.webshop.config.WebDriverConfig;
import ru.bulgakov.webshop.util.AttachManager;

import java.net.URI;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.getSelenoidChromeOptions;
import static ru.bulgakov.webshop.config.Config.getWebDriverConfig;

public class TestBase {

    private static final WebDriverConfig config = getWebDriverConfig();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = config.browserSize();
        Configuration.browser = config.browser();

        SelenideLogger.addListener(
                "allureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void createRemoteDriver() {
        if (!"remote".equals(System.getProperty("run"))) {
            return;
        }

        ClientConfig clientConfig = ClientConfig.defaultConfig()
                .baseUri(URI.create("https://" + config.selenoidUrl()))
                .authenticateAs(new UsernameAndPassword(
                        config.selenoidUser(), config.selenoidPassword()))
                .readTimeout(Duration.ofMinutes(2));

        WebDriverRunner.setWebDriver(RemoteWebDriver.builder()
                .oneOf(getSelenoidChromeOptions())
                .config(clientConfig)
                .build());

        ((RemoteWebDriver) WebDriverRunner.getWebDriver())
                .setFileDetector(new LocalFileDetector());
    }

    @AfterEach
    void after() {

        AttachManager.takeScreenshot();
        AttachManager.pageSource();
        AttachManager.browserConsoleLogs();

        if ("remote".equals(config.run())) {
            AttachManager.addVideo(sessionId().toString());
        }
        closeWebDriver();
    }
}