package ru.bulgakov.webshop.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.pages.WsProductPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
            void beforeEach() {
       authSteps.registerNewUser();
    }

    @Test
    void addItemToCartTest() {
        int processorIndex = 1;
        String itemQty = "3";

        WsProductPage productPage = open(WEB_SHOP_URL, WsWelcomePage.class)
                .hoverComputersCategory()
                .openDesktopsCategory()
                .openProductPage();

        String itemName = productPage.getItemNameFromProductPage();
        String itemPrice = productPage.getItemPriceFromProductPage();

        productPage.selectProcessor(processorIndex)
                .enterProductQty(itemQty)
                .addProductToCard()
                .checkBarNotificationSuccess()
                .checkQtyInHeaderCart(itemQty)
                .openHeaderCart()
                .checkProductNameInCart(itemName)
                .checkProductQtyInCart(itemQty)
                .checkTotalPriceInCart(itemPrice, itemQty, processorIndex);


        System.out.println(1);

    }
}
