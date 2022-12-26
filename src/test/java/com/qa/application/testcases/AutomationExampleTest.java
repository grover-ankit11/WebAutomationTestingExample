package com.qa.application.testcases;

import com.qa.application.pages.TestExample;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.lang.reflect.Method;


public class AutomationExampleTest extends BaseTest{
	public TestExample TEPage;
	@Test(dataProvider="getFromJson", priority=-1)
	public void titleTest(String title) {
		test = extent.createTest("Automation Example");
		test.assignCategory("Base Test");
		childTest = test.createNode("Validate Title of the Screen");
		log("Validating Title of the Screen");

		TEPage = page.getClassInstance(TestExample.class);
		Assert.assertEquals(TEPage.pageTitle(), title);
	}
	@Test(dataProvider="getFromJson")
	public void validLoginTest(String username, String password, String expectedMessage) throws InterruptedException {
		test.assignCategory("Positive Scenario");
		childTest = test.createNode("Validate login with valid username and password");
		log("Validating login with valid username and password");

		TEPage.login(username, password);
		Assert.assertEquals(TEPage.getPostSubmitMessage(), expectedMessage);
	}
	@Test(dataProvider="getFromJson")
	public void invalidUserTest(String username, String password, String expectedMessage) throws InterruptedException {
		test.assignCategory("Negative Scenario");
		childTest = test.createNode("Validate login with invalid username and valid password");
		log("Validating login with invalid username and valid password");

		TEPage.login(username, password);
		Assert.assertEquals(TEPage.getPostSubmitMessage(), expectedMessage);
	}
	@Test(dataProvider="getFromJson")
	public void invalidPasswordTest(String username, String password, String expectedMessage)
			throws InterruptedException {
		test.assignCategory("Negative Scenario");
		childTest = test.createNode("Validate login with valid username and invalid password");
		log("Validating login with valid username and invalid password");

		TEPage.login(username, password);
		Assert.assertEquals(TEPage.getPostSubmitMessage(), expectedMessage);
	}
	@DataProvider(name = "getFromJson")
	public Object[][] getFromJson(Method mtd) throws Exception {
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName());
		return dataMap;		
	}
}
