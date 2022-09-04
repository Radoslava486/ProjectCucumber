package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.BookingSearchPage;
import pages.BookingSearchResultsPage;

import java.util.concurrent.TimeUnit;

    public class BookingSearchSteps {
        private WebDriver driver;
        private static final String URL = "https://www.booking.com/searchresults.en-gb.html";
        private String hotelName;
        int index = 1;
        private BookingSearchPage bookingSearchPage;
        private BookingSearchResultsPage bookingSearchResultsPage;
        @Before
        public void initialize(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            bookingSearchPage = new BookingSearchPage(driver);
            bookingSearchResultsPage = new BookingSearchResultsPage(driver);

        }
        @After
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
        @Given("Hotel name for search is {string}")
        public void hotelNameForSearchIs(String name) {
            this.hotelName = name;
        }

        @When("I navigate to booking.com")
        public void iNavigateToBookingCom() {
            bookingSearchPage.open(URL);
        }

        @And("I enter search name")
        public void iEnterSearchName() {
            bookingSearchPage.inputHotelName(hotelName);
        }
        @And("I press search hotel button")
        public void iPressSearchHotelButton() {
            bookingSearchPage.clickSearchButton();
        }
        @Then("First result contains Hotel name in title")
        public void hotelWithNameIsPresentOnPage() {
            String firstHotelName = bookingSearchResultsPage.getHotelNameByIndex(index);
            boolean containsHotelName = firstHotelName.toLowerCase().trim().contains(hotelName.toLowerCase().trim());
            Assert.assertTrue(containsHotelName);
        }

        @And("Hotel has rating {string}")
        public void hotelHasRating(String rating) {
            String firstHotelRating = bookingSearchResultsPage.getHotelRatingByIndex(index);
            boolean containsHotelRating = firstHotelRating.trim().contains(rating.trim());
            Assert.assertTrue(containsHotelRating);
        }


    }
