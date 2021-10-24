package com.bdd.stepdefinitions;

// import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchingLaptop {

	private static WebDriver driver;

	private static void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Given("^I open chrome browser$")
	public void i_open_chrome_browser() throws Throwable {
		startBrowser();
	}

	@When("^I navigate to google page$")
	public void i_navigate_to_google_page() throws Throwable {
		driver.get("https://www.google.com/");
	}

	@When("^I enter laptop in the search field$")
	public void i_enter_laptop_in_the_search_field() throws Throwable {

		WebElement searchField = driver.findElement(By.name("q"));
		searchField.clear();
		searchField.sendKeys("laptop");

	}

	@When("^I click on search button$")
	public void i_click_on_search_button() throws Throwable {
		WebElement searchField = driver.findElement(By.name("q"));
		searchField.sendKeys(Keys.ENTER);
		Thread.sleep(2 * 1000);
	}

	@Then("^I should see search results$")
	public void i_should_see_search_results() throws Throwable {
		WebElement searchResultElem = driver.findElement(By.id("search"));

		List<WebElement> resultsH3Elems = searchResultElem.findElements(By.tagName("h3"));
		System.out.println("number of h3 results: " + resultsH3Elems.size());
		assertTrue(resultsH3Elems.size() > 0);

	}


	@When("^I enter Rose in the search field$")
	public void i_enter_Rose_in_the_search_field() throws Throwable {
		WebElement searchField = driver.findElement(By.name("q"));
		searchField.clear();
		searchField.sendKeys("rose");
	}
	
	@Then("^I validate the first search result Laptop$")
	public void i_validate_the_first_search_result_Laptop() throws Throwable {
		WebElement searchResultElem = driver.findElement(By.id("search"));
		List<WebElement> resultsH3Elems = searchResultElem.findElements(By.tagName("h3"));

		boolean isTextContain = false;
		int counter = 1;

		for (WebElement h3Elem : resultsH3Elems) {
			String h3Txt = h3Elem.getText();
			System.out.println("H3 number: " + counter + ", " + "h3Text: [ " + h3Txt + " ]");
			if (h3Txt.contains("Laptops - Amazon.com")) {
				isTextContain = true;
				break;
			}
			counter++;
		}

		assertTrue("Result text is NOT containing 'Laptops - Amazon.com' ", isTextContain);
	}

	@Then("^I validate the first search result Rose$")
	public void i_validate_the_first_search_result_Rose() throws Throwable {
		WebElement searchResultElem = driver.findElement(By.id("search"));
		List<WebElement> resultsH3Elems = searchResultElem.findElements(By.tagName("h3"));

		boolean isTextContain = false;
		int counter = 1;

		for (WebElement h3Elem : resultsH3Elems) {
			String h3Txt = h3Elem.getText();
			System.out.println("H3 number: " + counter + ", " + "h3Text: [ " + h3Txt + " ]");
			if (h3Txt.contains("Jasmine")) {
				isTextContain = true;
				break;
			}
			counter++;
		}

		assertTrue("Result text is NOT containing 'Jasmine' ", isTextContain);
	}
	
	
	@After
	public void endTest(Scenario scenario) {
		if (scenario.isFailed()) {

			try {

				String testStepName = scenario.getName();
				System.out.println("scenarioName: " + testStepName);

				String imagePath = "target/screenshot/" + testStepName + ".png";
				File file = new File(imagePath);
				String imageAbsolutePath = file.getAbsolutePath();
				Reporter.addScreenCaptureFromPath(imageAbsolutePath);

				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				Files.copy(src, new File(imagePath));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (driver != null) {
			driver.close();
			System.out.println("closing browser...");
		}
	}

}
