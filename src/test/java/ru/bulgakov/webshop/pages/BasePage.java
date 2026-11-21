package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasePage {

    private final ElementsCollection headerLinks = $$("div.header-links ul li a");
    private final SelenideElement headerProductQty = $("span.cart-qty");
    private final SelenideElement headerCartButton = $("a.ico-cart");

    @Step("Проверить авторизацию пользователя {email}")
    public BasePage checkUserLoggedIn(String email) {
        headerLinks.get(0).shouldHave(text(email));
        return this;
    }

    @Step("Проверить количество товаров в корзине {itemQty}")
    public BasePage checkQtyInHeaderCart(String itemQty) {
        headerProductQty.shouldHave(text("(" + itemQty + ")"));
        return this;
    }

    @Step("Открыть корзину")
    public WsCartPage openHeaderCart() {
        headerCartButton.click();
        return new WsCartPage();
    }
}
