package com.qa.quickstart.OrangeTestAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrangeAddEmployeePage {

	@FindBy(id = "firstName")
	WebElement firstName;
	@FindBy(id = "lastName")
	WebElement lastName;
	@FindBy(id = "chkLogin")
	WebElement createLoginCheck;
	@FindBy(id = "user_name")
	WebElement newUsername;
	@FindBy(id = "user_password")
	WebElement newPassword;
	@FindBy(id = "re_password")
	WebElement confirmPassword;
	@FindBy(id = "employeeId")
	WebElement IDNumber;
	@FindBy(id = "btnSave")
	WebElement saveButton;

	public OrangeAddEmployeePage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
	}

	public void addName(String forename, String surname) {
		firstName.sendKeys(forename);
		lastName.sendKeys(surname);
	}

	public void clickCreateLogin() {
		createLoginCheck.click();
	}

	public void createLoginDetails(String user, String pass) {

		newUsername.sendKeys(user);
		newPassword.sendKeys(pass);
		confirmPassword.sendKeys(pass);

	}

	public String getIDNumber() {
		return IDNumber.getAttribute("value");
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}

}
