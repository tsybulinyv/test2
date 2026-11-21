package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class WsProductPage extends BasePage {

    private final SelenideElement itemName = $("[itemprop=\"name\"]");
    private final SelenideElement itemPrice = $("[itemprop=\"price\"]");
    private final SelenideElement attributes = $("div.attributes");
    private final SelenideElement productQtyInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement barNotificationSuccess = $("div.bar-notification.success");

    @Step("Запомнить название товара")
    public String getItemNameFromProductPage() {
        return itemName.getText();
    }

    @Step("Запомнить цену товара")
    public String getItemPriceFromProductPage() {
        return itemPrice.getText();
    }

    @Step("Выбрать процессор")
    public WsProductPage selectProcessor(int processorIndex) {
        attributes.$$("dl dt")
                .findBy(text("Processor"))
                .sibling(0)
                .$$("li input")
                .get(processorIndex)
                .click();

        return this;
    }

    @Step("Ввести количество товара {itemQty}")
    public WsProductPage enterProductQty(String itemQty) {
        productQtyInput.setValue(itemQty);
        return this;
    }

    @Step("Добавить товар в корзину")
    public WsProductPage addProductToCart() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить появление уведомления о добавлении товара")
    public WsProductPage checkBarNotificationSuccess() {
        barNotificationSuccess.shouldBe(visible);
        return this;
    }
}