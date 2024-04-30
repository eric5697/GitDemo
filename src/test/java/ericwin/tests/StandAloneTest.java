package ericwin.tests;

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

import ericwin.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ADIDAS ORIGINAL";
		String countryName = "Indonesia";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("eric.win@mailwin.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ericwin123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> productElements = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = productElements.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//menggunakan visibilityOfElementLocated terkadang membuat atomation jadi lambat, coba diganti menjadi visibilityOf lalu tambahkan driver.findElement didalamnya
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean isProductExist = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(isProductExist);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".payment")));
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), countryName).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		List<WebElement> countriesElement = driver.findElements(By.cssSelector("button[class*='ta-item']"));
		WebElement countryElementSelected = countriesElement.stream().filter(country->country.findElement(By.cssSelector("span")).getText().equals(countryName)).findFirst().orElse(null);
		action.moveToElement(countryElementSelected).click().build().perform();
		action.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();
		
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.close();
	}

}
