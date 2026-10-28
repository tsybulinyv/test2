package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsWelcomePage {

    private final SelenideElement registerButton = $("a.ico-register");
    private final SelenideElement loginButton = $("a.ico-login");
    private final ElementsCollection headerMenu = $$("ul.top-menu li a");
    private final SelenideElement CategoryDesktopsButton = $(byText("Desktops"));

    public WsRegistrationPage openRegistration() {
        registerButton.click();
        return new WsRegistrationPage();
    }

    public WsLoginPage openLoginPage() {
        loginButton.click();
        return new WsLoginPage();
    }

    public WsWelcomePage hoverComputersCategory() {
        headerMenu.get(1).hover();
        return this;
    }

    public WsCategoryProductsPage openDesktopsCategory() {
        CategoryDesktopsButton.click();
        return new WsCategoryProductsPage();
    }
}
