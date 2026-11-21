package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MeetOrPayWindowPage {
    private final SelenideElement runToPayButton = $(byText("Бегу оплачивать"));

    @Step("Кликнуть на кнопку \"Бегу оплачивать\"")
    public MeetOrPayWindowPage runToPayButtonClick() {
        runToPayButton.click();

        return this;
    }
}
