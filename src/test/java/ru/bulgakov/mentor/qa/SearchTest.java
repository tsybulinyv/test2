package ru.bulgakov.mentor.qa;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.bulgakov.mentor.pages.*;
import ru.bulgakov.webshop.TestBase;

import static com.codeborne.selenide.Selenide.*;

public class SearchTest extends TestBase {

    @Test
    @Owner("y.tsybulin")
    @Tag("mentor")
    @Severity(SeverityLevel.NORMAL)
    @Link("TASK-1")
    @DisplayName("Проверка цены услуги ментора")
    @Description("Стоимость услуги на сайте должна быть 47000 руб.")
    void mentoringPriceShouldBe47000Test() {
//        Configuration.holdBrowserOpen = true;
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 10000;

        QaBulgakovWelcomePage qaBulgakovWelcomePage = new QaBulgakovWelcomePage();
        PromoCostPage promoCostPage = new PromoCostPage();
        MeetOrPayWindowPage meetOrPayWindowPage = new MeetOrPayWindowPage();
        LavaPaymentPage lavaPaymentPage = new LavaPaymentPage();

        open("https://ivanbulgakovqa.ru/");
        qaBulgakovWelcomePage.clickCostButton();
        promoCostPage.clickWantToRollButton();
        meetOrPayWindowPage.runToPayButtonClick();
        switchTo().window(1);
        lavaPaymentPage.checkPaymentPrice();
    }

    @Test
    @Owner("y.tsybulin")
    @Tag("DemoQA")
    @Severity(SeverityLevel.CRITICAL)
    @Link("TASK-2")
    @DisplayName("Проверка формы регистрации DemoQA")
    @Description("Заполняем форму регистрации")
    void demoQaPracticeFormTest() {
//                Configuration.holdBrowserOpen = true;
        DemoQaPracticeFormPage demoQaPracticeFormPage = new DemoQaPracticeFormPage();
                demoQaPracticeFormPage.openPage()
                .nameInput("Yaroslav")
                .lastNameInput("Tsybulin")
                .userEmailInput("example@gmail.com")
                .genderMaleSelect()
                .userNumberInput("8005553535")
                .dateOfBirthInput(7, "November", "1997")
                .subjectsInput("Maths")
                .hobbiesSportsSelect()
                .pictureUpload("Y:/1.png")
                .currentAddressInput("Ulitsa Pushkina, Dom Kolotushkina")
                .stateSelect("NCR")
                .citySelect("Delhi")
                .submitButtonClick()
                .resultModalShouldBeVisible()
                .resultTitleShouldHaveText("Thanks for submitting the form")
                .resultRowShouldHaveValue("Student Name", "Yaroslav Tsybulin")
                .resultRowShouldHaveValue("Student Email", "example@gmail.com")
                .resultRowShouldHaveValue("Gender", "Male")
                .resultRowShouldHaveValue("Mobile", "8005553535")
                .resultRowShouldHaveValue("Date of Birth", "07 November,1997")
                .resultRowShouldHaveValue("Subjects", "Maths")
                .resultRowShouldHaveValue("Address", "Ulitsa Pushkina, Dom Kolotushkina")
                .resultRowShouldHaveValue("State and City", "NCR Delhi");

    }
}