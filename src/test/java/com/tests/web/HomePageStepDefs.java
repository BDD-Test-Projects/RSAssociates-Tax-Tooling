package com.tests.web;

import com.ishaaniinfotech.data.ReadFromPropertiesFile;
import com.ishaaniinfotech.pageObject.*;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.SharedDriver.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class HomePageStepDefs {
    private static Home home;
    private static Login login;
    private static WebDriver driver;
    private static Scenario scenario;

    private static String environment = getExeEnvironment();
    private static ReadFromPropertiesFile readFromPropertiesFile = new ReadFromPropertiesFile(environment);

    public HomePageStepDefs(){
        driver = getDriver();
        scenario = getScenario();
    }


    @When("^I view the home page of the BBC News$")
    public void view_home_page_bbc_news() {
        String url = readFromPropertiesFile.readPropertiesFile("bbc.home");
        home = PageFactory.initElements(driver, Home.class);
        home.navigateTo(url);
    }

    @Then("^there should be a features section$")
    public void there_should_be_a_features_section() throws Throwable {
        assertTrue("Verify feature section", home.getFeatureSection());
    }

    @Then("^it should contain (\\d+) features stories$")
    public void it_should_contain_features_stories(int arg1) throws Throwable {
        assertTrue("Check feature section contains more than 8 stories",
                arg1 <= home.getFeaturesStories().size());
    }


    @Then("^when I click a story then I should be taken to view it's details$")
    public void when_I_click_a_story_then_I_should_be_taken_to_view_it_s_details() throws Throwable {
        String storyUrl = home.getStoryUrl(0);
        home.clickStory(0);

        assertTrue("Check if story on click goes to correct url to view details",
                home.getPageUrl().contains(storyUrl));
    }

    @When("^I click on the (.*) tab$")
    public void I_click_on_tab_name(String tabName) throws Throwable {
        home.clickTab(tabName);
    }

    @Then("^the title of the page displayed is (.*)$")
    public void the_title_of_the_page_displayed(String title){
        assertEquals("Verify Page Title", title, home.getPageTitle());
        scenario.write(title);
    }

    @Given ("^I'm not logged in$")
    public void I_am_not_logged_in(){
        String url = readFromPropertiesFile.readPropertiesFile("bbc.home");
        home = PageFactory.initElements(driver, Home.class);
        home.clearCookies();
        home.navigateTo(url);

    }

    @Then("^I should be presented with an option to sign in$")
    public void I_should_be_presented_with_an_option_to_sign_in(){
        assertTrue("Verify signIn link in home page", home.getSignIn());
    }

    @Given("^I'm logged in$")
    public void I_am_logged_in(){
        view_home_page_bbc_news();

        login=home.clickSignIn();
        home = login.loginValidCredential("test84", "test84");
    }

    @Then("^I should be presented with an option to sign out$")
    public void I_should_be_presented_with_an_option_to_sign_out() {
        home.clickSignIn();
        assertTrue("Verify signOut option",home.verifySignOut());        }

}