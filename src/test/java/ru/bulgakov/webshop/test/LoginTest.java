package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsLoginPage;
import ru.bulgakov.webshop.pages.WsRegistrationPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.*;

public class LoginTest extends TestBase {

        private static final Faker faker = new Faker();
        String email;
        String password = (faker.harryPotter().character()+faker.number().positive());;

        @Nested
        public class PositiveTests {
            @BeforeEach
            void beforeEach() {
                password = (faker.harryPotter().character()+faker.number().positive());
                email = faker.internet().emailAddress();

                open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                        .register(
                                faker.name().firstName(),
                                faker.name().lastName(),
                                email,
                                password)
                        .checkUserLoggedIn(email);
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
                        .enterEmail(email)
                        .enterPassword(password)
                        .rememberLoginAndPassword()
                        .submitLogin()
                        .checkUserLoggedIn(email);

                System.out.println(1);
            }

        }

        @ParameterizedTest
        @Owner("y.tsybulin")
        @Tag("DemoWebShop")
        @Severity(SeverityLevel.CRITICAL)
        @Link("TASK-5")
        @DisplayName("Авторизация с невалидным Email")
        @Description("Ввод невалидных значений для поля Email")
        @CsvFileSource(resources = ("/email.csv"))
        void invalidEmailLoginTest(String email) {
            open(WEB_SHOP_URL, WsWelcomePage.class)
                    .openLoginPage()
                    .enterEmail(email)
                    .enterPassword(password)
                    .verifyEmailValidationErrorAppear()
                    .submitLogin();
        }
}
