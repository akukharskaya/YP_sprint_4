package com.github.akukharskaya.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class OrderPage extends BasePage {

    //Кнопка заказа в хедере
    private final By orderButtonHeader = By.className("Button_Button__ra12g");
    //Кнопка заказа внизу страницы
    private final By orderButtonDown = By.className("Button_Middle__1CSJM");
    //Кнопка далее при оформлении заказа
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    //Поля для заполнения: имя, фамилия, адрес, номер телефона, даты, комментария
    private final By inputName = By.xpath(".//input[@placeholder='* Имя']");
    private final By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By inputPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By inputDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Выпадающий список станций метро
    private final By metroDropdown = By.className("select-search__input");
    //Выпадающий список срока аренды
    private final By rentalPeriod = By.xpath("//*[text()='* Срок аренды']");
    //Кнопка заказа
    private final By orderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Заказать']");
    //Кнопка подтверждения заказа
    private final By confirmOrder = By.xpath(".//button[text()='Да']");
    //Окно об успешном заказе
    private final By orderSuccess = By.className("Order_ModalHeader__3FDaJ");


    public OrderPage(WebDriver driver) {
        super(driver);
    }

    //Выбор кнопки для оформления заказа
    public void clickOrder(Boolean isHeaderButton) {
        if (isHeaderButton) {
            driver.findElement(orderButtonHeader).click();
            return;
        }
        scrollOrderButtonDown();
        driver.findElement(orderButtonDown).click();
    }

    //Клик по кнопке Далее для продолжения заполнения данных для заказа
    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    //Метод для введения данных: имя, фамилия,адрес, телефон
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }


    public void setSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void setPhone(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    //Выбор станции метро из DD, ожидание появления списка, клик по нужной станции
    public void selectMetroStation(String metro) {
        driver.findElement(metroDropdown).click();
        new WebDriverWait(driver, 5)
                .until(visibilityOfElementLocated(By.className("select-search__select")));
        scrollMetro(metro);

        WebElement button = driver.findElement(By.xpath("//*[text()='" + metro + "']/parent::button"));
        button.click();
    }

    //Метод для введения даты
    public void setDate(String date) {
        driver.findElement(inputDate).sendKeys(date);
    }

    //Выбор нужного срока аренды, клик
    public void selectRentalPeriod(String period) {
        driver.findElement(rentalPeriod).click();
        WebElement button = driver.findElement(By.xpath("//*[text()='" + period + "']"));
        button.click();
    }

    //Выбор цвета самоката
    public void setColours(Set<String> colors) {
        for (String color : colors) {
            WebElement button = driver.findElement(By.xpath("//*[text()='" + color + "']"));
            button.click();
        }
    }

    //Ввод коментария для курьера
    public void setComments(String comments) {
        driver.findElement(inputComment).sendKeys(comments);
    }

    //Клик по кнопке Заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Клик по кнопке подтверждения заказа(кнопка Да в всплывающем окошке)
    public void clickConfirmOrder() {
        driver.findElement(confirmOrder).click();
    }

    //Метод возращает текст окна заказа (для сравнения ОР с ФР)
    public String getOrderText() {
        return driver.findElement(orderSuccess).getText();
    }

    public void fillForm(String name, String surname, String address, String phone, String station, String period, String date, String comments, Set<String> colors) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setPhone(phone);
        selectMetroStation(station);
        clickNext();
        selectRentalPeriod(period);
        setDate(date);
        setComments(comments);
        setColours(colors);
        clickOrderButton();
    }

    //Скролл метро до нужной станции
    private void scrollMetro(String metro) {
        WebElement element = driver.findElement(By.xpath("//*[text()='" + metro + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Скролл до нижней кнопки Заказать
    private void scrollOrderButtonDown() {
        WebElement element = driver.findElement(orderButtonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}