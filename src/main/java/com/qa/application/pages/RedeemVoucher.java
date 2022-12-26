package com.qa.application.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedeemVoucher extends BasePage {

	public RedeemVoucher(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageLoadWait(wait);
	}
	
	//Locators
	By voucherCodeField = By.id("vouchercode");
	By voucherSubmitButton = By.id("vouchersubmit");
	By countryCodeField = By.xpath("//select[@name='countrycode']");
	By mobileNumberField = By.id("mobilenumber");
	By mobileNumberSubmitButton = By.id("mobilesubmit");
	By otpField = By.id("otp");
	By VerifyButton = By.xpath("//input[text()='Verify & Continue']");
	By postSubmitSuccessMessage = By.xpath("//div[@id='success']");
	By postSubmitErrorMessage = By.xpath("//div[@id='error']");
	
	//Actions
	
	public String pageTitle() {
		return driver.getTitle();
	}
	public void enterVoucherCode(String voucherCode) throws InterruptedException {
		inputDataToElement(voucherCodeField, voucherCode);
		driver.findElement(voucherSubmitButton).click();
		Thread.sleep(200);
	}
	public void enterMobileNumber(String countryCode, String mobileNumber) throws InterruptedException {
		inputDataToElement(countryCodeField, countryCode);
		inputDataToElement(mobileNumberField, mobileNumber);
		driver.findElement(mobileNumberSubmitButton).click();
		Thread.sleep(200);
	}
	public void enterOtp(String otp) throws InterruptedException {
		inputDataToElement(otpField, otp);
		driver.findElement(VerifyButton).click();
		Thread.sleep(200);
	}
	public String getPostSubmitMessage() {
		if(driver.findElements(postSubmitErrorMessage).size() > 0) {
			return driver.findElement(postSubmitErrorMessage).getText();
		} else{
			return getAlertMessage("Ok");
		}
	}
	public String getAlertMessage(String action) {
		String mainWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		if(action.contains("Ok"))
			alert.accept();
		else if(action.contains("Cancel"))
			alert.dismiss();
		driver.switchTo().window(mainWindow);
		return alertMessage;
	}
}
