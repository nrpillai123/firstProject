package firstFramework;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class StandAloneTest {
	
	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		String country = "India";		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("nileshrpillai@rediffmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shivansh@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, \"mb-3\")]"));
		System.out.println(products.size());
		
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean cart = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cart);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("IND");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group section")));
		List<WebElement>countries=driver.findElements(By.cssSelector(".ta-results button"));
		WebElement cont= countries.stream().filter(countrys-> 
		countrys.findElement(By.cssSelector(".list-group span")).getText().trim().equalsIgnoreCase(country)).findFirst().orElse(null);
		cont.click();
		driver.findElement(By.cssSelector(".actions a")).click();
		
		String confirmationMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmationMessage);
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
		
		
	}

	
}
