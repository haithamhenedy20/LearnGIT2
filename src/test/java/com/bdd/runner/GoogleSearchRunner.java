package com.bdd.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/test-report.html" },
		snippets = SnippetType.CAMELCASE,
		features = { "src/test/resources/features" },
		glue = { "com.bdd.stepdefinitions", "com.bdd.shared.stepdefinitions" },
		monochrome = true,
		tags = { "@Priority1", "@Smoke" } // if there is more than one tag in the parameter, 
		//than scenarios tagged with all tags mentioned in this parameter will run only
		//tags = { "@Regression" }
		
		)
public class GoogleSearchRunner {

	@AfterClass
	public static void writeExtentReport() {
		
	}
	
}
