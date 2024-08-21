package firstFramework;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import firstFramework.TestComponent.BaseTest;
import firtFramework.pageobjects.CartPage;
import firtFramework.pageobjects.CheckOutPage;
import firtFramework.pageobjects.ConfirmationPage;
import firtFramework.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test
	public void submitOrderTest() throws IOException {
		
		String productName = "ZARA COAT 3";
		String country = "India";		
		landingPage.loanApplication("nileshrpillai@rediffmail.com","Shivansh@1233");		
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		String country = "India";		
		ProductCatalogue productCatalogue = landingPage.loanApplication("nileshrpillai@rediffmail.com","Shivansh@123");
		productCatalogue.addProductToCard(productName);			
		CartPage myCartPage = productCatalogue.goToCartPage();
		Boolean match= myCartPage.validateCartProduct("ZARA COAT 33");
		Assert.assertFalse(match);		
		
	}
}
