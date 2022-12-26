package com.qa.application.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Page {
		
	public BasePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}
	
	
	// common functions for all the page classes
	
	// enter/click web element
	public void inputDataToElement(By by, String dataToInput)  {
		
		WebElement ele = driver.findElement(by);
		String tagName = ele.getTagName();
		
		if (tagName.equals("select")) {

			scrollToView(by);
			Select dropdown = new Select(ele);
			dropdown.selectByVisibleText(dataToInput);

		} else if (tagName.equals("textarea")) {

			scrollToView(by);
			ele.clear();
			ele.sendKeys(new CharSequence[] { dataToInput });
			ele.sendKeys(new CharSequence[] { Keys.TAB });
			
		} else if (tagName.equals("input")) {
			String inputType = ele.getAttribute("type");
			scrollToView(by);
			
			if ((inputType.equals("text")) || (inputType.equals("email")) || (inputType.equals("password"))) {
				ele.clear();
				ele.sendKeys(new CharSequence[] { dataToInput });
				ele.sendKeys(new CharSequence[] { Keys.TAB });
			}

			else if (inputType.equals("checkbox")) {
				if ((!ele.isSelected()) && (dataToInput.equals("1"))) {
					ele.click();

				} else if ((ele.isSelected()) && (dataToInput.equals("0"))) {
					ele.click();
				}
			}
			
			else if (inputType.equals("radio")) {
				if ((!ele.isSelected()) && (dataToInput.equals("1"))) {
					ele.click();
				}
			}
		}
	}
	// get page title
	public String GetTitle() {
		return driver.getTitle();
	}
	
	// scroll to element
	public void scrollToView(By by) {
		JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
		jsDriver.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}
	
	// pageloadTimeOut
	public void PageLoadWait(WebDriverWait wait) {	
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            wait.until(expectation);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
