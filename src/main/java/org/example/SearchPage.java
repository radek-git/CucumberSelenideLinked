package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchPage {

    List<String> resultLinks = new ArrayList<>();

    public void inputKeyWords(String keyWords) {
        $(Selectors.byAttribute("aria-controls", "job-search-bar-keywords-typeahead-list")).val(keyWords);
        actions().sendKeys(Keys.ENTER).build().perform();
    }

    public void inputRegion(String region) {
        $(Selectors.byAttribute("aria-controls", "job-search-bar-location-typeahead-list")).val(region);
        actions().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }
    


    public void selectTypeOfEmployment(String typeOfEmployment) {
        $(Selectors.byAttribute("data-tracking-control-name", "public_jobs_JOB_TYPE-dropdown")).click();
        $x("//ul[@class='filter-list__list']/li[@class='filter-list__list-item filter-button-dropdown__list-item']/label[contains(text(), '" + typeOfEmployment + "')]").click();
        $x("//div[@id='JOB_TYPE-dropdown']/fieldset/div/button").click();
    }

    public void selectLevelOfExperience(String levelOfExperience) {
        $x("//button[contains(text(), 'Poziom do')]").click();
        $x("//ul[@class='filter-list__list']/li/label[contains(text(), '" + levelOfExperience + "')]").click();
        $x("//div[@id='EXPERIENCE-dropdown']/fieldset/div/button").click();
    }

    public void clickOnResultAndVerifyCriteria(String criteria) {
        List<SelenideElement> results = $$(Selectors.byXpath("//ul[@class='jobs-search__results-list']/li"));
        System.out.println("Lista wynikow: " + results.size());
        for (SelenideElement result : results) {
            result.click();
//            $(Selectors.byClassName("result-card__full-card-link")).click();
            $(Selectors.byClassName("show-more-less-html__button--more")).waitUntil(Condition.visible, 5000).click();
            String description = $(Selectors.byClassName("show-more-less-html__markup")).getText();
            int count = 0;

            String[] criteriaTab = criteria.split(" ");
            for (String s : criteriaTab) {
                if (description.contains(s)) {
                    count++;
                }
            }
            if (count > 5) {
                resultLinks.add(WebDriverRunner.currentFrameUrl());
            }

            System.out.println(count);
        }
    }


    public void printResults() {
        for (String resultLink : resultLinks) {
            System.out.println("Link do oferty: " + resultLink);
        }
    }



}
