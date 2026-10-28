package ru.bulgakov.webshop.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WsCartPage {
    private final SelenideElement productName = $("a.product-name");
    private final SelenideElement itemQtyInCart = $("input.qty-input");
    private final SelenideElement totalPriceInCart = $("span.product-subtotal");

    public WsCartPage checkProductNameInCart(String itemName) {
        productName.shouldHave(text(itemName));
        return this;
    }

    public WsCartPage checkProductQtyInCart(String itemQty) {
        String valueQtyInCart = itemQtyInCart.getAttribute("value");
        assertEquals(itemQty, valueQtyInCart);
        return this;
    }

    public WsCartPage checkTotalPriceInCart(String itemPrice, String itemQty, int processorIndex) {
        double price = Double.parseDouble(itemPrice);
        double surcharge = 0;

        if (processorIndex == 1) {
            surcharge = 15;
        } else if (processorIndex == 2) {
            surcharge = 100;
        }

        double expectedPrice = (price + surcharge) * Double.parseDouble(itemQty);
        double actualPrice = Double.parseDouble(totalPriceInCart.getText());
        assertEquals(expectedPrice, actualPrice);

        return this;
    }
}
