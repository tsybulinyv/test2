package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WsRegistrationPage {

    private final SelenideElement maleGenderRadio = $("input#gender-male");
    private final SelenideElement pageTitle = $("div.page-title");
    private final SelenideElement firstNameInput = $("input#FirstName");
    private final SelenideElement lastNameInput = $("input#LastName");
    private final SelenideElement emailInput = $("input#Email");
    private final SelenideElement passwordInput =  $("input#Password");
    private final SelenideElement confirmPasswordInput =  $("input#ConfirmPassword");
    private final SelenideElement submitRegistrationButton =  $("input#register-button");
    private final SelenideElement resultText =  $("div.result");
    private final ElementsCollection headerLinks =  $$("div.header-links ul li a");

    @Step("Зарегистрировать уникального пользователя через интерфейс {firstName}, {lastName}, {email}, {password}")
    public WsRegistrationPage register(String firstName, String lastName, String email, String password) {
        selectMaleGender()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .confirmPassword(password)
                .submitRegistration()
                .checkRegistrationCompleted();
        return this;
    }

    @Step("Проверить, что открыта страница регистрации")
    public WsRegistrationPage verifyRegistrationOpened() {
        pageTitle.shouldHave(text("Register"));
        return this;
    }

    @Step("Выбрать мужской пол")
    public WsRegistrationPage selectMaleGender() {
        maleGenderRadio.click();
        return this;
    }

    @Step("Ввести имя {firstName}")
    public WsRegistrationPage enterFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввести фамилию {lastName}")
    public WsRegistrationPage enterLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввести электронную почту {email}")
    public WsRegistrationPage enterEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Ввести пароль {password}")
    public WsRegistrationPage enterPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Подтвердить пароль {password}")
    public WsRegistrationPage confirmPassword(String password) {
        confirmPasswordInput.setValue(password);
        return this;
    }

    @Step("Подтвердить регистрацию")
    public WsRegistrationPage submitRegistration() {
        submitRegistrationButton.click();
        return this;
    }

    @Step("Убeдиться, что регистрация завершена")
    public WsRegistrationPage checkRegistrationCompleted() {
        resultText.shouldHave(text("Your registration completed"));
        return this;
    }

    @Step("Проверить электронную почту зарегестрированного пользователя {email}")
    public WsRegistrationPage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }

}
