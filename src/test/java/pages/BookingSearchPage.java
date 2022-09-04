package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingSearchPage extends BasePage{

    private final static By INPUT_LOCATOR = By.xpath("//input[@name='ss']");
    private final static By SEARCH_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");

    public BookingSearchPage(WebDriver driver) {
        super(driver);
    }

    public void inputHotelName(String hotelName){
        driver.findElement(INPUT_LOCATOR).sendKeys(hotelName);
    }
    public void clickSearchButton(){
        driver.findElement(SEARCH_BUTTON_LOCATOR).click();
    }

    public void open(String URL) {
        driver.get(URL);
    }
}

