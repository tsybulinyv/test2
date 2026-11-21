package ru.bulgakov.webshop.steps;

import io.qameta.allure.Step;
import net.datafaker.Faker;
import ru.bulgakov.webshop.pages.WsRegistrationPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_REGISTRATION_URL;

public class AuthSteps {

    private static final Faker faker = new Faker();

    private final String email = faker.internet().emailAddress();
    private final String password =
            faker.harryPotter().character() + faker.number().positive();

    @Step("арегистрировать нового пользователя с почтой {email} и паролем {password}")
    public void registerNewUser() {
        open(WEB_SHOP_REGISTRATION_URL, WsRegistrationPage.class)
                .register(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        email,
                        password)
                .checkUserLoggedIn(email);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
