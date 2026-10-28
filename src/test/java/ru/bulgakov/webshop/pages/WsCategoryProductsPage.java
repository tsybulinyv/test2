package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$;

public class WsCategoryProductsPage {
    private final ElementsCollection productGrid = $$("div.product-grid div");

    public WsProductPage openProductPage() {
        productGrid.get(0).click();
        return new WsProductPage();
    }
}
