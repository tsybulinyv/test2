package ru.bulgakov.webshop.test;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import ru.bulgakov.webshop.TestBase;
import ru.bulgakov.webshop.pages.WsCartPage;
import ru.bulgakov.webshop.pages.WsProductPage;
import ru.bulgakov.webshop.pages.WsWelcomePage;
import ru.bulgakov.webshop.steps.AuthSteps;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.bulgakov.webshop.config.Config.WEB_SHOP_URL;

public class CartTest extends TestBase {

    private static final String PRODUCT_NAME = "Build your own cheap computer";

    private final AuthSteps authSteps = new AuthSteps();

    @BeforeEach
    void beforeEach() {
        authSteps.registerNewUser();
    }

    @Test
    @Owner("y.tsybulin")
    @Tags({@Tag("UI"), @Tag("positive"), @Tag("DemoWebShop")})
    @Severity(SeverityLevel.CRITICAL)
    @Link("TASK-3")
    @DisplayName("Проверка добавления товара в корзину")
    @Description("Добавляем товар в корзину и проверяем соответствие его параметров и цены")
    void addItemToCartTest() {

        int processorIndex = 2;
        String itemQty = "3";

        WsProductPage productPage = open(WEB_SHOP_URL, WsWelcomePage.class)
                .hoverComputersCategory()
                .openDesktopsCategory()
                .openProduct(PRODUCT_NAME);

        double itemPrice = Double.parseDouble(productPage.getItemPriceFromProductPage());

        double expectedUnitPrice = itemPrice + processorSurcharge(processorIndex);
        double expectedTotalPrice = expectedUnitPrice * Integer.parseInt(itemQty);

        WsCartPage cartPage = productPage
                .selectProcessor(processorIndex)
                .enterProductQty(itemQty)
                .addProductToCart()
                .checkBarNotificationSuccess()
                .checkQtyInHeaderCart(itemQty)
                .openHeaderCart();

        assertAll(
                () -> assertEquals(PRODUCT_NAME, cartPage.getProductName()),
                () -> assertEquals(itemQty, cartPage.getQty()),
                () -> assertEquals(expectedUnitPrice, Double.parseDouble(cartPage.getUnitPrice())),
                () -> assertEquals(expectedTotalPrice, Double.parseDouble(cartPage.getTotalPrice()))
        );
    }

    private double processorSurcharge(int processorIndex) {
        return switch (processorIndex) {
            case 0 -> 0.0;
            case 1 -> 15.0;
            case 2 -> 100.0;
            default -> throw new IllegalArgumentException(
                    "Unknown processor index: " + processorIndex
            );
        };
    }
}
