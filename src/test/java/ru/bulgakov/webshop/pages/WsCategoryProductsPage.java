package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$$;

public class WsCategoryProductsPage {

    private final ElementsCollection productTitles = $$("h2.product-title a");

    @Step("Открыть карточку товара: {productName}")
    public WsProductPage openProduct(String productName) {
        productTitles.findBy(exactText(productName)).click();
        return new WsProductPage();
    }
}
