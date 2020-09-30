import com.codeborne.selenide.Configuration;
import io.cucumber.java8.En;
import org.example.SearchPage;

import static com.codeborne.selenide.Selenide.open;

public class SearchingOffersSteps implements En {

    private SearchPage searchPage;

    public SearchingOffersSteps() {

        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;

        Given("User opens page", () -> {
            open("https://pl.linkedin.com/jobs/search?keywords=&location=&geoId=&trk=public_jobs_jobs-search-bar_search-submit&redirect=false&position=1&pageNum=0");
        });

        Given("User inputs key words {string} in search field", (String keyWords) -> {
            searchPage = new SearchPage();
            searchPage.inputKeyWords(keyWords);
        });

        Given("User inputs region {string}", (String region) -> {
            searchPage.inputRegion(region);
        });

        Given("User selects type of employment {string}", (String typeOfEmployment) -> {
            searchPage.selectTypeOfEmployment(typeOfEmployment);
        });

        Given("User selects level of experience {string}", (String levelOfExperience) -> {
            searchPage.selectLevelOfExperience(levelOfExperience);
        });

        When("User reads all offers by criteria {string}", (String criteria) -> {
            searchPage.clickOnResultAndVerifyCriteria(criteria);
        });

        Then("Offers are saved", () -> {
            searchPage.printResults();
        });
    }
}
