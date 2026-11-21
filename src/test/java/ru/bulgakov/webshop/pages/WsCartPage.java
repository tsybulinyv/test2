package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class WsCartPage {

    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement itemQtyInCart = $("input.qty-input");
    private final SelenideElement unitPriceInCart = $(".product-unit-price");
    private final SelenideElement totalPriceInCart = $("span.product-subtotal");

    @Step("Получить название товара из корзины")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Получить количество товара в корзине")
    public String getQty() {
        return itemQtyInCart.getValue();
    }

    @Step("Получить цену за единицу товара")
    public String getUnitPrice() {
        return unitPriceInCart.getText();
    }

    @Step("Получить общую стоимость товаров в корзине")
    public String getTotalPrice() {
        return totalPriceInCart.getText();
    }
}
