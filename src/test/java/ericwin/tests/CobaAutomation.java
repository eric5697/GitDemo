package ericwin.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CobaAutomation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String originCityName = "Yogyakarta";
		String originAirportId = "";
		String destinationCityName = "Bali";
		String destinationAirportId = "";
		String startMonthYear = "Oktober 2024";
		String startDate = "5";
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://www.tiket.com/");
		
		driver.findElement(By.xpath("//a[@href='/pesawat' and @data-testid='vertical-icon-link']")).click();
		driver.findElement(By.cssSelector("div[data-testid='clickable-departure-input']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='Modal_modal_popup']")));
		driver.findElement(By.cssSelector("input[placeholder='Masukkan nama kota atau bandara.']")).sendKeys(originCityName);
		List<WebElement> airportsFromList = driver.findElements(By.cssSelector(".AirportForm_airport__SE2YS"));
		if (!originAirportId.isEmpty()) {
			WebElement airportElement = airportsFromList.stream().filter(airport -> airport.findElement(By.cssSelector(".AirportForm_airport_code__rj_BI")).getText().equals(originAirportId)).findFirst().orElse(null);
			airportElement.click();
		} else {
			WebElement airportElement = airportsFromList.stream().filter(airport -> airport.findElement(By.cssSelector(".List_center_side__VcPj_ p:first-of-type")).getText().contains(originCityName)).findFirst().orElse(null);
			airportElement.click();
		}
		
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("section[class*='Modal_modal_popup']")));
		driver.findElement(By.cssSelector("div[data-testid='clickable-arrival-input']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='Modal_modal_popup']")));
		driver.findElement(By.cssSelector("input[placeholder='Masukkan nama kota atau bandara.']")).sendKeys(destinationCityName);
		List<WebElement> airportsDestinationList = driver.findElements(By.cssSelector(".AirportForm_airport__SE2YS"));
		if (!destinationAirportId.isEmpty()) {
			WebElement airportElement = airportsDestinationList.stream().filter(airport -> airport.findElement(By.cssSelector(".AirportForm_airport_code__rj_BI")).getText().equals(destinationAirportId)).findFirst().orElse(null);
			airportElement.click();
		} else {
			WebElement airportElement = airportsDestinationList.stream().filter(airport -> airport.findElement(By.cssSelector(".List_center_side__VcPj_ p:first-of-type")).getText().contains(destinationCityName)).findFirst().orElse(null);
			airportElement.click();
		}
		
		driver.findElement(By.xpath("//div[contains(@class,'SearchForm_date_input__xPYSQ')]/div")).click();

		
		WebElement monthElement = null;
		do {
			List<WebElement> calendarsElement = driver.findElements(By.cssSelector(".CalendarDesktop_month__grzJo"));
			monthElement = calendarsElement.stream().filter(calendar -> calendar.findElement(By.cssSelector(".CalendarDesktop_month_heading_container__zqSy0")).getText().equals(startMonthYear)).findFirst().orElse(null);
			if(monthElement == null) {
				driver.findElement(By.cssSelector(".CalendarDesktop_next_button__T6rGr")).click();
			}
		} while (monthElement == null);
		
		List<WebElement> daysElement = monthElement.findElements(By.cssSelector(".Day_day_number__rQ1Bc"));
		WebElement dayElement = daysElement.stream().filter(day -> day.getText().equals(startDate)).findFirst().orElse(null);
		dayElement.click();
		
//		WebElement monthElement = null;
//		do {
//			List<WebElement> calendarMonthsElement = driver.findElements(By.cssSelector(".CalendarDesktop_month_heading_container__zqSy0"));
//			monthElement = calendarMonthsElement.stream().filter(month -> month.getText().equals(startMonthYear)).findFirst().orElse(null);
//			if(monthElement == null) {
//				driver.findElement(By.cssSelector(".CalendarDesktop_next_button__T6rGr")).click();
//			}
//		} while (monthElement == null);
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector(".SearchForm_btn_submit__sURLN"))).click().build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ProgressBar_progress_wrapper__9Pb_w")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ProgressBar_progress_wrapper__9Pb_w")));
		
//		driver.findElements(By.cssSelector("div[data-testid='srp-flight-card']"));
	}

}
