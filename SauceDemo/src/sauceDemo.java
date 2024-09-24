import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class sauceDemo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.driver.chromedriver", "C:\\Users\\divya.jain\\edgedriver_win64");
		WebDriver driver = new ChromeDriver();
		// To login in to the System
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
      
		// To add multiple product randomly 
				
		String[] itemsNeeded = { "Backpack", "Bike", "Bolt" };
		addToCart(driver, itemsNeeded);

		// Add single product to cart
		// driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		// WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		// w.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("shopping_cart_link")));
		// to go to top of page.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.HOME);
		WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
		driver.findElement(By.id("shopping_cart_container")).click();
        
		//To check if item is added in cart or not
		// String Inteventory=driver.findElement(By.className("inventory_item_name")).getText();
		// Assert.assertEquals(Inteventory, "Sauce Labs Bike Light");

		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Divya");
		driver.findElement(By.id("last-name")).sendKeys("jain");
		driver.findElement(By.id("postal-code")).sendKeys("12345");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("finish")).click();
		String message = driver.findElement(By.className("complete-header")).getText();
		// Assertion to check the correct string
		Assert.assertEquals(message, "Thank you for your order!");
		// To close current instance of browser
		//driver.close();

	}

	private static void addToCart(WebDriver driver, String[] itemsNeeded)

	{
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name ']"));

		List al = Arrays.asList(itemsNeeded);

		for (int i = 0; i < products.size(); i++) {
			String itemName = products.get(i).getText();

			String[] item = itemName.split(" ");
			// Sauce Labs Bike Light
			// item[0]=Sauce
			// item[1]= Labs
			// item[2]=Bike
			// item[3]=Light
			String itemFormattedName = item[2].trim();

			if (al.contains(itemFormattedName)) {

				driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory ']")).get(i)
						.click();

			}
		}

		   System.out.println("Test1");
		   System.out.println("Test2");
	}

 

}
