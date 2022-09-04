package steps;

import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.YandexSearchPage;
import pages.YandexSearchResultsPage;

import java.util.concurrent.TimeUnit;

public class SearchSteps {
    private WebDriver driver;
    private String searchWord;
    private YandexSearchPage yandexSearchPage;
    private YandexSearchResultsPage searchResultsPage;
    @Before
    public void initialize(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        yandexSearchPage = new YandexSearchPage(driver);
        searchResultsPage = new YandexSearchResultsPage(driver);

    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Given("Word for search is {string}")
    public void wordForSearchIs(String word) {
        this.searchWord = word;
    }
    @When("I navigate to yandex.by")
    public void iNavigateToYandexBy() {
        yandexSearchPage.open();
    }
    @And("I enter search word")
    public void iEnterSearchWord() {
        yandexSearchPage.setSearchInputValue(searchWord);
    }
    @And("I press search button")
    public void iPressSearchButton() {
        yandexSearchPage.clickSearchButton();
    }
    @Then("First result contains search word in title")
    public void firstResultContainsSearchWordInTitle() {
        String firstResultText = searchResultsPage.getSearchResultTitleByIndex(1);
        boolean doesContains = firstResultText.toLowerCase().trim().contains(searchWord.toLowerCase().trim());
        Assert.assertTrue(doesContains);

    }


    @Then("There are {int} result pages on the first search page")
    public void thereAreResultPagesOnTheFirstSearchPage(int arg0) {
        Assert.assertEquals(searchResultsPage.getSearchResultsCount(), arg0);
    }
}