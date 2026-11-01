package ru.bulgakov.mentor.pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class DemoQaPracticeFormPage {

    private final SelenideElement nameTextField = $("#firstName");
    private final SelenideElement lastnameTextField = $("#lastName");
    private final SelenideElement userEmailTextField = $("#userEmail");
    private final SelenideElement genderMaleButton = $("input[name='gender'][value='Male']");
    private final SelenideElement userNumberTextField = $("#userNumber");
    private final SelenideElement dateOfBirthField = $("#dateOfBirthInput");
    private final SelenideElement dateOfBirthYearSelect = $(".react-datepicker__year-select");
    private final SelenideElement dateOfBirthMonthSelect = $(".react-datepicker__month-select");
    private final SelenideElement subjectsTextField = $("#subjectsInput");
    private final SelenideElement hobbiesSportsButton = $("#hobbies-checkbox-1");
    private final SelenideElement pictureUploadField = $("#uploadPicture");
    private final SelenideElement currentAddressTextField = $("#currentAddress");
    private final SelenideElement stateSelect = $("#react-select-3-input");
    private final SelenideElement citySelect = $("#react-select-4-input");
    private final SelenideElement submitButton = $("#submit");

    @Step("Открыть страницу с формой DemoQA")
    public DemoQaPracticeFormPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        return this;
    }

    @Step("Ввести имя {name}")
    public DemoQaPracticeFormPage nameInput(String name) {
        nameTextField.setValue(name);
        return this;
    }

    @Step("Ввести фамилию {lastName}")
    public DemoQaPracticeFormPage lastNameInput(String lastName) {
        lastnameTextField.setValue(lastName);
        return this;
    }

    @Step("Ввести электронную почту {email}")
    public DemoQaPracticeFormPage userEmailInput(String email) {
        userEmailTextField.setValue(email);
        return this;
    }

    @Step("Выбрать мужской пол")
    public DemoQaPracticeFormPage genderMaleSelect() {
        genderMaleButton.click();
        return this;
    }

    @Step("Ввести телефон {number}")
    public DemoQaPracticeFormPage userNumberInput(String number) {
        userNumberTextField.setValue(number);
        return this;
    }

    @Step("Заполнить дату рождения {day}, {month}, {year}")
    public DemoQaPracticeFormPage dateOfBirthInput(int day, String month, String year) {

        dateOfBirthField.click();
        dateOfBirthYearSelect.selectOption(year);
        dateOfBirthMonthSelect.selectOption(month);

        String dayClass = String.format("%03d", day);
        $(".react-datepicker__day--" + dayClass + ":not(.react-datepicker__day--outside-month)").click();
        return this;
    }

    @Step("Заполнить предметы {subject}")
    public DemoQaPracticeFormPage subjectsInput(String subject) {
        subjectsTextField.setValue(subject);
        $$(".subjects-auto-complete__option").findBy(text(subject)).click();
        return this;
    }

    @Step("Заполнить поле о хобби")
    public DemoQaPracticeFormPage hobbiesSportsSelect() {
        hobbiesSportsButton.click();
        return this;
    }

    @Step("Загрузить аватар")
    public DemoQaPracticeFormPage pictureUpload(String filePath) {
        pictureUploadField.uploadFile(new File(filePath));
        return this;
    }

    @Step("Ввести адрес {address}")
    public DemoQaPracticeFormPage currentAddressInput(String address) {
        currentAddressTextField.setValue(address);
        return this;
    }

    @Step("Выбрать страну {state}")
    public DemoQaPracticeFormPage stateSelect(String state) {
        stateSelect.scrollIntoView(true).click();
        $$("[id^='react-select-3-option-']").findBy(text(state)).click();
        return this;
    }

    @Step("Выбрать город {city}")
    public DemoQaPracticeFormPage citySelect(String city) {
        citySelect.scrollIntoView(true).click();
        $$("[id^='react-select-4-option-']").findBy(text(city)).click();
        return this;
    }

    @Step("Подтвердить отправку формы")
    public DemoQaPracticeFormPage submitButtonClick() {
        submitButton.scrollIntoView(true).click();
        return this;
    }

    @Step("Проверить, что после отправки формы появилось информационное окно")
    public DemoQaPracticeFormPage resultModalShouldBeVisible() {
        $(".modal-content").shouldBe(visible);
        return this;
    }

    @Step("Проверить, что в названии информационного окна есть текст {expectedTitle}")
    public DemoQaPracticeFormPage resultTitleShouldHaveText(String expectedTitle) {
        $("#example-modal-sizes-title-lg").shouldHave(text(expectedTitle));
        return this;
    }

    @Step("Проверить таблицу информационного окна")
    public DemoQaPracticeFormPage resultRowShouldHaveValue(String rowName, String expectedValue) {

        $$(".modal-content tbody tr").findBy(text(rowName)).shouldHave(text(expectedValue));
        return this;
    }
}