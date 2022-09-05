package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookingSearchResultsPage extends BasePage{
    private final static By HOTELS_LOCATOR = By.xpath("//div[@data-testid='title']");
    private final static By RATINGS = By.xpath("//div[contains(@aria-label,'Scored ')]");
    public BookingSearchResultsPage(WebDriver driver) {
        super(driver);
    }
    public String getHotelNameByIndex(int index) {
        return driver.findElements(HOTELS_LOCATOR).get(index -1).getText();
    }
    public String getHotelRatingByIndex(int index) {
        return driver.findElements(RATINGS).get(index -1).getText();
    }

}
