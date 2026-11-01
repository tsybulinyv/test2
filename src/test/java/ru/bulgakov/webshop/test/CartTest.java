package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsProductPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.*;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest extends TestBase {

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
            void beforeEach() {
       authSteps.registerNewUser();
    }

    @Test
    @Owner("y.tsybulin")
    @Tag("DemoWebShop")
    @Severity(SeverityLevel.CRITICAL)
    @Link("TASK-3")
    @DisplayName("Проверка добавления товара в корзину")
    @Description("Добавляем товар в корзину и проверяем соответствие его параметров и цены")

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
