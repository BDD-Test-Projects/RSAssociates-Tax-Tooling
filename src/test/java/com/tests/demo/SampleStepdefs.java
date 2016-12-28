package com.tests.demo;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Vinod on 27/10/2016.
 */
public class SampleStepdefs {


    @Given("^I make a maven project with all dependencies$")
    public void iMakeAMavenProjectWithAllDependencies() throws Throwable {

        System.out.println("\n Yes this Given step was executed well");

    }

    @When("^I compile this project in CLI$")
    public void iCompileThisProjectInCLI() throws Throwable {

        System.out.println("\n Yes this When step was executed well");

    }

    @Then("^I should be able to get detailed HTML Report as per this developer$")
    public void iShouldBeAbleToGetDetailedHTMLReportAsPerThisDeveloper() throws Throwable {

        System.out.println("\n Yes this Then step was executed well");

    }
}
