package ru.bulgakov.mentor.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class QaBulgakovWelcomePage {
    private final SelenideElement costButton = $$(".t-menu__list li").last();

    @Step("Кликнуть на кнопку \"Стоимость\"")
    public QaBulgakovWelcomePage clickCostButton() {
        costButton.click();

        return this;
    }
}
