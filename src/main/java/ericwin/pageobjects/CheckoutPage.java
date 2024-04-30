package ericwin.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ericwin.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	Actions action;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement selectCountryInput;
	
	@FindBy(css="button[class*='ta-item']")
	private List<WebElement> countriesElement;
	
	@FindBy(css=".action__submit")
	private WebElement placeOrderButton;
	
	private By paymentPageBy = By.cssSelector(".payment");
	private By countryInputOptionBy = By.cssSelector(".ta-results");
	
	public void fillCheckoutInformation(String countryName) {
		waitForElementToAppear(paymentPageBy);
		action.sendKeys(selectCountryInput, countryName).build().perform();
		waitForElementToAppear(countryInputOptionBy);
		WebElement countryElementSelected = countriesElement.stream().filter(country->country.findElement(By.cssSelector("span")).getText().equals(countryName)).findFirst().orElse(null);
		action.moveToElement(countryElementSelected).click().build().perform();
		action.moveToElement(placeOrderButton).click().build().perform();
	}
	
	public ConfirmationPage submitOrder() {
		action.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
