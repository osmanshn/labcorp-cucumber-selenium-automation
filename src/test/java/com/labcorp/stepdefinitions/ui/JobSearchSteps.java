package com.labcorp.stepdefinitions.ui;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/job_search.feature")
public class JobSearchSteps {

    private WebDriver driver;

    @Before("@ui_testing")
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/osmansahin/LabCorpBDDSeleniumAutomation/src/main/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("I am on the LabCorp website")
    public void gotoMainPage() {
        driver.get("https://www.labcorp.com");
    }

    @When("I navigate to the Careers page")
    public void navigateToCareersPage() {
        WebElement careersLink = driver.findElement(By.linkText("Careers"));
        careersLink.click();
    }

    @When("I search for a {string} job")
    public void searchForAjob(String jobTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
        searchInput.sendKeys(jobTitle);
        searchInput.sendKeys(Keys.RETURN);
    }

    @And("I select the first job listing {string}")
    public void selectTheFirstJobListing(String jobTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement firstJobLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(jobTitle)));
        firstJobLink.click();
    }

    @Then("I should see the Job Title is {string}")
    public void verifyJobTitle(String expectedJobTitle) {
        String actualJobTitle = driver.findElement(By.className("job-title")).getText();
        Assert.assertEquals(expectedJobTitle, actualJobTitle);
    }

    @And("I should see the Job Location is {string}")
    public void verifyJobLocation(String expectedJobLocation) {
        String actualJobLocationText = driver.findElement(By.className("job-location")).getText();
        String actualJobLocation = actualJobLocationText.substring(actualJobLocationText.length() - expectedJobLocation.length());
        Assert.assertEquals(expectedJobLocation, actualJobLocation);
    }

    @And("I should see the Job Id is {string}")
    public void verifyJobId(String expectedJobId) {
        String actualJobIdText = driver.findElement(By.className("jobId")).getText();
        String actualJobId = actualJobIdText.substring(actualJobIdText.length() - expectedJobId.length());
        Assert.assertEquals(expectedJobId, actualJobId);
    }

    @And("I should see second sentence of first paragraph under description is {string}")
    public void verifyFirstParagraphOfDescription(String expectedDescription) {
        String paragraph = driver.findElement(By.xpath("//div[@class='jd-info au-target']")).getText();
        String[] sentences = paragraph.split("\\.");
        String secondSentence = sentences.length > 1 ? sentences[1].trim() : "";
        Assert.assertEquals(expectedDescription, secondSentence);
    }

    @And("I should see third requirement is {string}")
    public void verifyThirdRequirement(String expectedRequirement) {
        String actualRequirement = driver.findElement(By.xpath("//div[contains(@class, 'jd-info')]//ul[2]/li[3]")).getText();
        Assert.assertEquals(expectedRequirement, actualRequirement);
    }

    @And("I should see fourth responsibility is {string}")
    public void verifyFourthResponsibility(String expectedResponsibility) {
        String actualResponsibility = driver.findElement(By.xpath("//div[contains(@class, 'jd-info')]//ul[1]/li[4]")).getText();
        Assert.assertEquals(expectedResponsibility, actualResponsibility);
    }

    @When("I click on the Back to search results button")
    public void clickBackToSearchResultsButton() {
        WebElement backToSearchResultsButton = driver.findElement(By.xpath("//*[@data-ph-at-id='back-to-search-results-text']"));
        backToSearchResultsButton.click();
    }

    @Then("I should see the search results page")
    public void verifyTitle() {
        assertTrue(driver.getTitle().contains("Search results | Available job openings at Labcorp"));
    }

    @After("@ui_testing")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
