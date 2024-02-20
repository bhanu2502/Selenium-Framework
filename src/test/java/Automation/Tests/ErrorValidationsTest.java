package Automation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;
import Automation.pageobjects.CartPage;
import Automation.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		

		ProductCatalogue productCatalogue = landingPage.loginApplication("bhanu123@gmail.com", "Bhanu124");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMesage());
			

	}
	@Test
	public void productValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("bhanu123@gmail.com", "Bhanu123");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyproductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
