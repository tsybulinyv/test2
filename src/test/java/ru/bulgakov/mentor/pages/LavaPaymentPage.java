package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LavaPaymentPage {
    private final SelenideElement paymentPrice = $("h3[data-at='H3'][class*='price']");
    private final SelenideElement currencySelectorMenu = $("div.ant-select-selector");
    private final ElementsCollection currencyList = $$(".ant-select-item-option");

    @Step("Открыть выпадающий список выбора валют")
    public LavaPaymentPage openCurrencySelector() {
        currencySelectorMenu.click();
        return this;
    }

    @Step("Выбрать рубли")
    public LavaPaymentPage chooseCurrencyRub() {
        currencyList.findBy(text("RUB")).click();
        return this;
    }

    @Step("Проверить цену услуги на странице")
    public LavaPaymentPage checkPaymentPrice() {
        paymentPrice.shouldHave(text("47 000.00"));
        return this;
    }
}
