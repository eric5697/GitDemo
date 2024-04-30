package ericwin.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class cobaFirefox {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("eric.win@mailwin.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ericwin123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-toastr"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".card"))));
		
		//cobacoba
		driver.findElement(By.xpath("(//input[@type='checkbox'])[21]")).sendKeys(Keys.PAGE_DOWN);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//input[@type='checkbox'])[21]"))));
		driver.findElement(By.xpath("(//input[@type='checkbox'])[21]")).click();
		
//		List<WebElement> productsElement = driver.findElements(By.cssSelector(".card"));
//		WebElement productSelected = productsElement.stream().filter(product -> product.findElement(By.cssSelector("h5 b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
//		productSelected.findElement(By.cssSelector("button:last-of-type")).click();
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-toastr"))));
//		
//		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".totalRow button"))));
//		
//		driver.findElement(By.cssSelector(".totalRow button")).sendKeys(Keys.PAGE_DOWN);
		
		//TO-DO : scroll to the button first then click
//		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		driver.quit();
		
	}

}
