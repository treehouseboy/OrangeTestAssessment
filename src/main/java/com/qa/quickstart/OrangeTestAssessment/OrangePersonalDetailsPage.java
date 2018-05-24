package com.qa.quickstart.OrangeTestAssessment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangePersonalDetailsPage {

	@FindBy(id = "welcome")
	WebElement welcomeButton;
	@FindBy(id = "personal_txtEmpFirstName")
	WebElement firstName;
	@FindBy(id = "personal_txtEmpLastName")
	WebElement lastName;
	@FindBy(id = "personal_txtEmployeeId")
	WebElement IDNumber;

	public OrangePersonalDetailsPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
	}

	public void logout(WebDriver driver) {
		welcomeButton.click();

		WebElement logoutButton = new WebDriverWait(driver, 5).until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"welcome-menu\"]/ul/li[2]/a")));

		logoutButton.click();
	}

	public String getFirstName() {
		return firstName.getAttribute("value");
	}

	public String getLastName() {
		return lastName.getAttribute("value");
	}

	public String getIDNumber() {
		return IDNumber.getAttribute("value");

	}

}
