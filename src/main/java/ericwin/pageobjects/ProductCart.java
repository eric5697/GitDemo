package ericwin.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ericwin.AbstractComponents.AbstractComponent;

public class ProductCart extends AbstractComponent {
	WebDriver driver;
	Actions action;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public ProductCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	
	
	public Boolean verifyProductDisplay(String productName) {
		Boolean isProductExist = productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return isProductExist;
	}
	
	public CheckoutPage goToCheckoutPage() {
		action.moveToElement(checkoutButton).click().build().perform();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
