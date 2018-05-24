package com.qa.quickstart.OrangeTestAssessment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class OrangeDashboardPage {
	
	@FindBy(xpath = "//*[@id=\"menu_pim_viewPimModule\"]/b") WebElement PIMTab;
	@FindBy(xpath = "//*[@id=\"menu_pim_addEmployee\"]") WebElement addEmployeeButton;
	
	public OrangeDashboardPage(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3), this);
	}
	
	public void hoverOverPIMTab(WebDriver driver) {
		
		Actions action = new Actions(driver);
		action.moveToElement(PIMTab).perform();
		
	}
	
	public void clickAddEmployeeButton() {
		addEmployeeButton.click();
	}

}
