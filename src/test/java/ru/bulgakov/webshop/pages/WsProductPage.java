package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public String getItemNameFromProductPage() {
        return itemName.getText();
    }

    public String getItemPriceFromProductPage() {
        return itemPrice.getText();
    }

    public WsProductPage selectProcessor(int processorIndex) {
        // processorIndex 0 = slow, 1 = medium, 2 = fast
        processorOptions.get(processorIndex).click();
        return this;
    }

    public WsProductPage enterProductQty(String itemQty) {
        productQtyInput.setValue(itemQty);
        return this;
    }

    public WsProductPage addProductToCard() {
        addToCartButton.click();
        return this;
    }

    public WsProductPage checkBarNotificationSuccess() {
        barNotificationSuccess.shouldBe(visible);
        return this;
    }

    public WsProductPage checkQtyInHeaderCart(String itemQty) {
        headerProductQty.shouldHave(text("(" + itemQty + ")"));
        return this;
    }

    public WsCartPage openHeaderCart() {
        headerCartButton.click();
        return new WsCartPage();
    }
}
