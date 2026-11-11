package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsWelcomePage;

import static com.codeborne.selenide.Selenide.open;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class RegistrationTest extends TestBase {

    private static final Faker faker = new Faker();

    @Test
    @Owner("y.tsybulin")
    @Tags({@Tag("UI"), @Tag("positive"), @Tag("DemoWebShop")})
    @Severity(SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature("Регистрация")
    @Story("Регистрация нового пользователя")
    @Link("TASK-6")
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Регистрируем пользователя через интерфейс")
    void registrationTest() {

        String password = faker.harryPotter().character() + faker.number().positive();
        String email = faker.internet().emailAddress();

        open(WEB_SHOP_URL, WsWelcomePage.class)
                .openRegistration()
                .verifyRegistrationOpened()
                .selectMaleGender()
                .enterFirstName(faker.name().firstName())
                .enterLastName(faker.name().lastName())
                .enterEmail(email)
                .enterPassword(password)
                .confirmPassword(password)
                .submitRegistration()
                .checkRegistrationCompleted()
                .checkUserLoggedIn(email);
    }
}