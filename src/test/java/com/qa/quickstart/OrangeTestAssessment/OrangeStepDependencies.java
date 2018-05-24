package com.qa.quickstart.OrangeTestAssessment;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;;

public class OrangeStepDependencies {

	WebDriver driver;
	OrangeLoginPage loginPage;
	OrangeDashboardPage dashboardPage;
	OrangeAddEmployeePage addEmployeePage;
	OrangePersonalDetailsPage personalDetailsPage;
	ExtentTest test;
	String IDNumber;
	ExtentReports report = new ExtentReports(Constants.extentReportLocation, true);

	@Before
	public void setup() {
		System.setProperty(Constants.chromeDriver, Constants.chromeDriverLocation);
		driver = new ChromeDriver();
		test = report.startTest("Test to add a new user.");
		driver.manage().window().maximize();
	}

	@Given("^the Add Employee Tab$")
	public void the_Add_Employee_Tab() {

		driver.get(Constants.loginURL);

		driver.manage().window().maximize();

		test.log(LogStatus.INFO, "Reached Login Page");

		loginPage = new OrangeLoginPage(driver);

		loginPage.enterDetails(Constants.USERNAME, Constants.PASSWORD);

		loginPage.clickLoginButton();

		test.log(LogStatus.INFO, "Reached Dashboard");

		dashboardPage = new OrangeDashboardPage(driver);

		dashboardPage.hoverOverPIMTab(driver);

		dashboardPage.clickAddEmployeeButton();

		test.log(LogStatus.INFO, "Reached Add Employee Page");

		addEmployeePage = new OrangeAddEmployeePage(driver);
	}

	@When("^I fill out the Employee Details correctly$")
	public void i_fill_out_the_Employee_Details_correctly() {

		addEmployeePage.addName(Constants.FIRST_NAME, Constants.SURNAME);

		test.log(LogStatus.INFO, "Employee details entered");
	}

	@When("^I choose to create Login Details$")
	public void i_choose_to_create_Login_Details() {

		addEmployeePage.clickCreateLogin();

		test.log(LogStatus.INFO, "Create Login box checked");
	}

	@When("^I fill out the Login Details correctly$")
	public void i_fill_out_the_Login_Details_correctly() {

		addEmployeePage.createLoginDetails(Constants.NEW_USER, Constants.NEW_PASS);

		IDNumber = addEmployeePage.getIDNumber();

		test.log(LogStatus.INFO, "Login details saved");
	}

	@When("^I click the Save button$")
	public void i_click_the_Save_button() {

		addEmployeePage.clickSaveButton();

		test.log(LogStatus.INFO, "Employee details saved");
	}

	@Then("^I can see information about the user$")
	public void i_can_see_information_about_the_user() {

		personalDetailsPage = new OrangePersonalDetailsPage(driver);

		if (personalDetailsPage.getIDNumber().equals(IDNumber)) {

			test.log(LogStatus.PASS, "Employee Login successfully created");

		} else {

			test.log(LogStatus.PASS, "Employee Login creation failed");
		}
		Assert.assertEquals(IDNumber, personalDetailsPage.getIDNumber());
		Assert.assertEquals(Constants.FIRST_NAME, personalDetailsPage.getFirstName());
		Assert.assertEquals(Constants.SURNAME, personalDetailsPage.getLastName());
	}

	@When("^I log out and enter the new login details$")
	public void i_log_out_and_enter_the_new_login_details() {

		personalDetailsPage.logout(driver);

		test.log(LogStatus.INFO, "Successfully logged out");

		loginPage.enterDetails(Constants.NEW_USER, Constants.NEW_PASS);
	}

	@When("^I click the login button$")
	public void i_click_the_login_button() {

		loginPage.clickLoginButton();

	}

	@Then("^I can successfully log on$")
	public void i_can_successfully_log_on() {

		if (driver.findElement(By.xpath("//*[@id=\"welcome\"]")).getText().equals("Welcome George")) {

			test.log(LogStatus.PASS, "Successfully logged back in");

		} else {

			test.log(LogStatus.FAIL, "Log back in failed");
		}

		Assert.assertEquals("Welcome George", driver.findElement(By.xpath("//*[@id=\"welcome\"]")).getText());
	}

	@After
	public void teardown() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}

}
