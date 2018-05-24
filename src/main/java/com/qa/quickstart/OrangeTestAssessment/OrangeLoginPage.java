package com.qa.quickstart.OrangeTestAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrangeLoginPage {
	
	@FindBy(id = "txtUsername") WebElement username;
	@FindBy(id = "txtPassword") WebElement password;
	@FindBy(id = "btnLogin") WebElement loginButton;
	
	
	public OrangeLoginPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
	}
	
	public void enterDetails(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}

}
