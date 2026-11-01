package ru.bulgakov.webshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import ru.bulgakov.webshop.util.AttachManager;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sessionStorage;

public class TestBase {

    @BeforeAll
    static void setUpAllure() {
        Configuration.browserSize = "1920x1080";

        SelenideLogger.addListener(
                "allure",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterEach
    void after(TestInfo testInfo) {
        String testName = testInfo.getDisplayName();

        AttachManager.takeScreenshot("Screenshot - " + testName);
        AttachManager.pageSource();
        AttachManager.browserConsoleLogs();

        cookies().clear();
        localStorage().clear();
        sessionStorage().clear();
    }

    /*
    @BeforeEach
    void closeDriver() {
        closeWebDriver();
        */


}
