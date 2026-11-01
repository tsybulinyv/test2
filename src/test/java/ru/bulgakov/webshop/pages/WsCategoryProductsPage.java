package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class WsCategoryProductsPage {
    private final ElementsCollection productGrid = $$("div.product-grid div");

    @Step("Открыть карточку товара")
    public WsProductPage openProductPage() {
        productGrid.get(0).click();
        return new WsProductPage();
    }
}
