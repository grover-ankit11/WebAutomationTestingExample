package com.qa.application.testcases;

import com.qa.application.pages.RedeemVoucher;
import com.qa.application.util.JsonDataManager;
import com.qa.application.util.JsonDataProvider;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class RedeemVoucherTest extends BaseTest{
	public RedeemVoucher RVPage;
	@Test(dataProvider="getFromJson", priority=-1)
	public void titleTest(String title) {
		test = extent.createTest("Redeem Voucher Page Tests");
		test.assignCategory("Base Test");
		childTest = test.createNode("Validate Title of the Screen");
		log("Validating Title of the Screen");

		RVPage = page.getClassInstance(RedeemVoucher.class);
		Assert.assertEquals(RVPage.pageTitle(), title);
	}
	@Test(dataProvider="getFromJson")
	public void validVoucherCodeRedeemTest(String voucherCode, String countryCode, String mobileNumber, String otp,
									   String expectedMessage) throws InterruptedException {
		test.assignCategory("Positive Scenario");
		childTest = test.createNode("Validate voucher redeem with valid voucher code, valid country code, "
				+ "valid mobile number and valid OTP");
		log("Validating voucher redeem with valid voucher code, valid country code, "
				+ "valid mobile number and valid OTP");

		RVPage.enterVoucherCode(voucherCode);
		RVPage.enterMobileNumber(countryCode, mobileNumber);
		RVPage.enterOtp(otp);
		Assert.assertEquals(RVPage.getPostSubmitMessage(), expectedMessage);
	}
	@Test(dataProvider="getFromJson")
	public void invalidVoucherCodeTest(String voucherCode, String expectedMessage) throws InterruptedException {
		test.assignCategory("Negative Scenario");
		childTest = test.createNode("Validate voucher redeem with invalid voucher code");
		log("Validating voucher redeem with invalid voucher code");

		RVPage.enterVoucherCode(voucherCode);
		Assert.assertEquals(RVPage.getPostSubmitMessage(), expectedMessage);
	}
	@Test(dataProvider="getFromJson")
	public void invalidCountryCodeTest(String voucherCode, String countryCode, String mobileNumber,
									   String expectedMessage) throws InterruptedException {
		test.assignCategory("Negative Scenario");
		childTest = test.createNode("Validate voucher redeem with valid voucher code, invalid country code "
				+ "and valid mobile number ");
		log("Validating voucher redeem with valid voucher code, invalid country code "
				+ "and valid mobile number");

		RVPage.enterVoucherCode(voucherCode);
		RVPage.enterMobileNumber(countryCode, mobileNumber);
		Assert.assertEquals(RVPage.getPostSubmitMessage(), expectedMessage);
	}
	@Test(dataProvider="getFromJson")
	public void invalidMobileNumberTest(String voucherCode, String countryCode, String mobileNumber,
									   String expectedMessage) throws InterruptedException {
		test.assignCategory("Negative Scenario");
		childTest = test.createNode("Validate voucher redeem with valid voucher code, valid country code "
				+ "and invalid mobile number ");
		log("Validating voucher redeem with valid voucher code, valid country code "
				+ "and invalid mobile number");

		RVPage.enterVoucherCode(voucherCode);
		RVPage.enterMobileNumber(countryCode, mobileNumber);
		Assert.assertEquals(RVPage.getPostSubmitMessage(), expectedMessage);
	}
	@Test(dataProvider="getFromJson")
	public void wrongOtpTest(String voucherCode, String countryCode, String mobileNumber, String otp,
										   String expectedMessage) throws InterruptedException {
		test.assignCategory("Negative Scenario");
		childTest = test.createNode("Validate voucher redeem with valid voucher code, valid country code, "
				+ "valid mobile number and wrong OTP");
		log("Validating voucher redeem with valid voucher code, valid country code, "
				+ "valid mobile number and wrong OTP");

		RVPage.enterVoucherCode(voucherCode);
		RVPage.enterMobileNumber(countryCode, mobileNumber);
		RVPage.enterOtp(otp);
		Assert.assertEquals(RVPage.getPostSubmitMessage(), expectedMessage);
	}
	@DataProvider(name = "getFromJson")
	public Object[][] getFromJson(Method mtd) throws Exception {
		JsonDataManager jdm = JsonDataProvider.loadData();
		Object[][] dataMap = jdm.getData(getClass(), mtd.getName());
		return dataMap;		
	}
}
