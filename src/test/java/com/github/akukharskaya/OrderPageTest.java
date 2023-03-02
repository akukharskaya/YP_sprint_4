package com.github.akukharskaya;

import com.github.akukharskaya.pages.OrderPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Set;


@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest {
    private final Boolean isHeaderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;

    private final String date;
    private final String period;
    private final Set<String> colors;
    private final String comments;

    //Два набора тестовых данных
    public OrderPageTest(Boolean isHeaderButton, String name, String surname, String address, String station, String phone,
                         String date, String period, Set<String> colors, String comments) {
        this.isHeaderButton = isHeaderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.colors = colors;
        this.comments = comments;
    }

    @Parameterized.Parameters(name = "Тест. Тестовые данные: Кнопка в хедере: {0} Имя: {1}, Город: {3}")
    public static Object[][] parameters() {
        return new Object[][]{
                {true, "Анна", "Анна", "Москва", "Технопарк", "77777777777", "01.03.2023", "двое суток", Set.of("чёрный жемчуг"), "Привезите к 15:00"},
                {false, "Денис", "Ден", "Москва", "Фили", "799999999999", "28.02.2023", "трое суток", Set.of("серая безысходность"), "Спасибо"}
        };
    }


    @Test
    public void testOrder() {
        OrderPage page = new OrderPage(driver);
        page.clickOrder(isHeaderButton);
        page.fillForm(name, surname, address, phone, station, period, date, comments, colors);
        page.clickConfirmOrder();

        String expected = "Заказ оформлен";
        String actual = page.getOrderText();
        //!!! Тест на Chrome падает из-за некликабельности кнопки "Да" при оформлении заказа, для корректного тестирования используйте Firefox
        Assert.assertEquals("Заказ не оформлен. Оформить его можно в браузере Firefox", expected, actual);
    }


}
