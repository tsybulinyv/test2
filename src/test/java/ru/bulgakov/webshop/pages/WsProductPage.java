package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WsProductPage {
    private final SelenideElement itemName = $("[itemprop=\"name\"]");
    private final SelenideElement itemPrice = $("[itemprop=\"price\"]");
    private final ElementsCollection processorOptions = $$("dl dd ul").get(0).$$("li input");
    private final SelenideElement productQtyInput = $("input.qty-input");
    private final SelenideElement addToCartButton = $("input.add-to-cart-button");
    private final SelenideElement barNotificationSuccess = $("div.bar-notification.success");
    private final SelenideElement headerProductQty = $("span.cart-qty");
    private final SelenideElement headerCartButton = $("a.ico-cart");

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
        // processorIndex 0 = slow, 1 = medium, 2 = fast
        processorOptions.get(processorIndex).click();
        return this;
    }

    @Step("Ввести количество товара {itemQty}")
    public WsProductPage enterProductQty(String itemQty) {
        productQtyInput.setValue(itemQty);
        return this;
    }

    @Step("Добавить товар в корзину")
    public WsProductPage addProductToCard() {
        addToCartButton.click();
        return this;
    }

    @Step("Проверить появление уведомления о добавлении товара")
    public WsProductPage checkBarNotificationSuccess() {
        barNotificationSuccess.shouldBe(visible);
        return this;
    }

    @Step("Проверить количество {itemQty} добавленного товара в корзину")
    public WsProductPage checkQtyInHeaderCart(String itemQty) {
        headerProductQty.shouldHave(text("(" + itemQty + ")"));
        return this;
    }

    @Step("Открыть корзину")
    public WsCartPage openHeaderCart() {
        headerCartButton.click();
        return new WsCartPage();
    }
}
