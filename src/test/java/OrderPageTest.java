import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.OrderPage;

import java.util.Set;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


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

    @Parameterized.Parameters
    public static Object[][] parameters(){
        return new Object[][]{
                {true, "Анна", "Анна", "Москва", "Технопарк", "77777777777", "01.03.2023", "двое суток", Set.of("чёрный жемчуг"), "Привезите к 15:00"},
                {false, "Денис","Ден","Москва","Фили","799999999999","28.02.2023","трое суток", Set.of("серая безысходность"),"Спасибо"}
        };
    }


    @Test
    public void testOrder() {
        OrderPage page = new OrderPage(driver);
        page.clickOrder(isHeaderButton);
        page.setName(name);
        page.setSurname(surname);
        page.setAddress(address);
        page.setPhone(phone);
        page.selectMetroStation(station);
        page.clickNext();

        page.selectRentalPeriod(period);
        page.setDate(date);
        page.setComments(comments);
        page.setColours(colors);
        page.clickOrderButton();
        page.clickConfirmOrder();

        String expected = "Заказ оформлен";
        String actual = page.getOrderText();

        //!!! Тест на Chrome падает из-за некликабельности кнопки "Да" при оформлении заказа, для корректного тестирования используйте Firefox
        assertThat("Заказ не оформлен. Оформить его можно в браузере Firefox",actual,containsString(expected));
    }


}
