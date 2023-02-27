package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    //Метод, кликающий на вопросы, открывающий ответы
    public void clickQuestion(int num){
        scroll();
        String id = "accordion__heading-" + num;
        WebElement element = driver.findElement(By.id(id));

        element.click();
    }
    //Метод, возвращающий текст ответов на конкретный вопрос
    public String getAnswer(int num){
        String id = "accordion__panel-" + num;
        WebElement element = driver.findElement(By.id(id));

        return element.getText();
    }


    public void scroll(){
        WebElement element = driver.findElement(By.className("accordion"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}

