package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final ElementsCollection headerMenu = $$("ul.top-menu li a");
    private final SelenideElement categoryDesktopsButton = $(byText("Desktops"));

    @Step("Нажать на кнопку регистрации")
    public WsRegistrationPage openRegistration() {
        registerButton.click();
        return new WsRegistrationPage();
    }

    @Step("Нажать на кнопку авторизации")
    public WsLoginPage openLoginPage() {
        loginButton.click();
        return new WsLoginPage();
    }

    @Step("Навести курсор на категорию \"Компьютеры\"")
    public WsWelcomePage hoverComputersCategory() {
        headerMenu.get(1).hover();
        return this;
    }

    @Step("Открыть категорию \"Desktops\"")
    public WsCategoryProductsPage openDesktopsCategory() {
        categoryDesktopsButton.click();
        return new WsCategoryProductsPage();
    }
}