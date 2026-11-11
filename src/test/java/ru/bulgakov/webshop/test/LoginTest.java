package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsLoginPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.*;

public class LoginTest extends TestBase {

    private static final String INVALID_EMAIL_TEST_PASSWORD = "TestPassword123!";

    @Nested
    public class PositiveTests {

        private final AuthSteps authSteps = new AuthSteps();

        @BeforeEach
        void beforeEach() {
            authSteps.registerNewUser();

            cookies().clear();
            localStorage().clear();
            sessionStorage().clear();
        }

        @Test
        @Owner("y.tsybulin")
        @Tag("DemoWebShop")
        @Severity(SeverityLevel.CRITICAL)
        @Link("TASK-4")
        @DisplayName("Проверка успешной авторизации")
        @Description("Проверяем вход пользователя через интерфейс")
        void successLoginTest() {

            open(WEB_SHOP_LOGIN_URL, WsLoginPage.class)
                    .verifyLoginPageOpened()
                    .enterEmail(authSteps.getEmail())
                    .enterPassword(authSteps.getPassword())
                    .rememberLoginAndPassword()
                    .submitLogin()
                    .checkUserLoggedIn(authSteps.getEmail());
        }
    }

    @ParameterizedTest
    @Owner("y.tsybulin")
    @Tag("DemoWebShop")
    @Severity(SeverityLevel.CRITICAL)
    @Link("TASK-5")
    @DisplayName("Авторизация с невалидным Email")
    @Description("Ввод невалидных значений для поля Email")
    @CsvFileSource(resources = "/email.csv")
    void invalidEmailLoginTest(String email) {

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openLoginPage()
                .enterEmail(email)
                .enterPassword(INVALID_EMAIL_TEST_PASSWORD)
                .verifyEmailValidationErrorAppear()
                .submitLogin();
    }
}