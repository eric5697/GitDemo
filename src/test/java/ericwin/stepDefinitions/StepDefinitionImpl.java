package ericwin.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ericwin.TestComponents.BaseTest;
import ericwin.pageobjects.CheckoutPage;
import ericwin.pageobjects.ConfirmationPage;
import ericwin.pageobjects.LandingPage;
import ericwin.pageobjects.ProductCart;
import ericwin.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> productElements = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Fill input country (.+), checkout (.+) and submit the order$")
	public void fill_input_checkout_submit_order(String country, String productName) {
		ProductCart productCart = productCatalogue.goToCartPage();
		Boolean isProductExist = productCart.verifyProductDisplay(productName);
		Assert.assertTrue(isProductExist);
		
		CheckoutPage checkoutPage = productCart.goToCheckoutPage();
		checkoutPage.fillCheckoutInformation(country);
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_confirmationPage(String expectedMessage) {
		Boolean isConfirmationMessageSuccess = confirmationPage.verifyConfirmationMessage();
		Assert.assertTrue(isConfirmationMessageSuccess);
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void incorrect_message_is_displayed(String expectedMessage) {
		Assert.assertEquals(expectedMessage, landingPage.getErrorMessage());
		driver.close();
	}
}
