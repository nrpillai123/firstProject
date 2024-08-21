package firstFramework;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
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
import org.testng.annotations.Test;
import org.testng.internal.BaseClassFinder;

import firstFramework.TestComponent.BaseTest;
import firtFramework.pageobjects.LandingPage;
import firtFramework.pageobjects.CartPage;
import firtFramework.pageobjects.CheckOutPage;
import firtFramework.pageobjects.ConfirmationPage;
import firtFramework.pageobjects.ProductCatalogue;



public class SubmitOrderTest extends BaseTest{
	@Test
	public void submitOrderTest() throws IOException {
		
		String productName = "ZARA COAT 3";
		String country = "India";		
		ProductCatalogue productCatalogue = landingPage.loanApplication("nileshrpillai@rediffmail.com","Shivansh@123");
		productCatalogue.addProductToCard(productName);			
		CartPage myCartPage = productCatalogue.goToCartPage();
		Boolean cart= myCartPage.validateCartProduct(productName);
		Assert.assertTrue(cart);		
		CheckOutPage checkOutPage = myCartPage.goToCheckout();
		checkOutPage.selectCountry(country);
		ConfirmationPage confirmationPage =checkOutPage.submitOrder();
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		System.out.println(confirmationMessage);
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

	
}
