package ericwin.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ericwin.TestComponents.BaseTest;
import ericwin.TestComponents.Retry;
import ericwin.pageobjects.CheckoutPage;
import ericwin.pageobjects.ConfirmationPage;
import ericwin.pageobjects.LandingPage;
import ericwin.pageobjects.ProductCart;
import ericwin.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("eric.win@mailwin.com", "EricwinSalahPassword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		String countryName = "Indonesia";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("eric.win3@mailwin.com", "Ericwin123");
		List<WebElement> productElements = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		
		ProductCart productCart = productCatalogue.goToCartPage();
		Boolean isProductExist = productCart.verifyProductDisplay("ADIDAS KW");
		Assert.assertFalse(isProductExist);
	}

}
