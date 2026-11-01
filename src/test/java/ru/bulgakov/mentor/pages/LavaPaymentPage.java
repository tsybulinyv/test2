package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LavaPaymentPage {
    private final SelenideElement paymentPrice = $("h3[data-at='H3'][class*='price']");

    @Step("Проверить цену услуги на странице")
    public LavaPaymentPage checkPaymentPrice() {
        paymentPrice.shouldHave(text("47 000.00"));
        return this;
    }
}
