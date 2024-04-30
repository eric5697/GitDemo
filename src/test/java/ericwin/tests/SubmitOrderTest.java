package ericwin.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ericwin.TestComponents.BaseTest;
import ericwin.pageobjects.CheckoutPage;
import ericwin.pageobjects.ConfirmationPage;
import ericwin.pageobjects.LandingPage;
import ericwin.pageobjects.OrderPage;
import ericwin.pageobjects.ProductCart;
import ericwin.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		String countryName = "Indonesia";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> productElements = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		
		ProductCart productCart = productCatalogue.goToCartPage();
		Boolean isProductExist = productCart.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(isProductExist);
		
		CheckoutPage checkoutPage = productCart.goToCheckoutPage();
		checkoutPage.fillCheckoutInformation(countryName);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		Boolean isConfirmationMessageSuccess = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(isConfirmationMessageSuccess);
	}
	
	//Verify if ADIDAS ORIGINAL displaying in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("eric.win@mailwin.com", "Ericwin123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap("\\src\\test\\java\\ericwin\\data\\PurchaseOrder.json");
		
		return new Object[][] { {data.get(0)}, {data.get(1)} };
	}
	
//	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "eric.win@mailwin.com");
//		map.put("password", "Ericwin123");
//		map.put("product", "ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "eric.win2@mailwin.com");
//		map1.put("password", "Ericwin123");
//		map1.put("product", "ZARA COAT 3");
//		
//		return new Object[][] {{map}, {map1}};
//	}

}
