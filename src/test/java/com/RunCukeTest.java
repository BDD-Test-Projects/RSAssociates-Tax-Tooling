package com;

/**
 * Created by Vinod on 27/10/2016.
 */


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
        tags={"@Test"}
        )
public class RunCukeTest {



}


