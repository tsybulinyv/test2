package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WsLoginPage extends BasePage {

    private final SelenideElement loginTitle = $("div.page-title h1");
    private final SelenideElement loginEmailInput = $("input.email");
    private final SelenideElement loginPasswordInput = $("input.password");
    private final SelenideElement loginCheckboxRememberMe = $("input#RememberMe");
    private final SelenideElement loginSubmitButton = $("input.login-button");
    private final SelenideElement invalidEmailNotification = $("span.field-validation-error");

    @Step("Убедиться в том, что открыта страница авторизации")
    public WsLoginPage verifyLoginPageOpened() {
        loginTitle.shouldHave(text("Welcome, Please Sign In!"));
        return this;
    }

    @Step("Ввести электронную почту {email}")
    public WsLoginPage enterEmail(String email) {
        loginEmailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль {password}")
    public WsLoginPage enterPassword(String password) {
        loginPasswordInput.setValue(password);
        return this;
    }

    @Step("Кликнуть «Запомнить меня»")
    public WsLoginPage rememberLoginAndPassword() {
        loginCheckboxRememberMe.click();
        return this;
    }

    @Step("Подтвердить вход")
    public WsLoginPage submitLogin() {
        loginSubmitButton.click();
        return this;
    }

    @Step("Проверить, что появляется ошибка валидации")
    public WsLoginPage verifyEmailValidationErrorAppear() {
        invalidEmailNotification.shouldBe(visible);
        return this;
    }
}
