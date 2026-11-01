package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class PromoCostPage {
    private final SelenideElement wantToRollButton = $x("/html/body/div[1]/div[42]/div/div/div[32]/div/a/div/span");

    @Step("Кликнуть на кнопку \"Хочу вкатиться\"")
    public PromoCostPage clickWantToRollButton() {
        wantToRollButton.click();

        return this;
    }
}
