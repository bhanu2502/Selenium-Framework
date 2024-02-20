package Automation.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Automation.TestComponents.BaseTest;
import Automation.pageobjects.CartPage;
import Automation.pageobjects.CheckOutPage;
import Automation.pageobjects.ConfirmationPage;
import Automation.pageobjects.LandingPage;
import Automation.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImp extends BaseTest {
	public LandingPage landingPage;
	ProductCatalogue productCatalogue ;
	CheckOutPage checkOutPage;
	ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) {
		 productCatalogue = landingPage.loginApplication(username, password);
	}
	@When("^I add the product (.+) to cart$")
	public void add_the_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage= productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyproductDisplay(productName);
		Assert.assertTrue(match);
	   checkOutPage = cartPage.goToCheckout();
		checkOutPage.selectCountry("India");
		checkOutPage.submitOrder();
	}
	@Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
	@Then("^\"([^\"]*)\" message is displayed$")
	public void some_message_is_displayed(String strArg1) throws Throwable{
		Assert.assertEquals(strArg1, landingPage.getErrorMesage());
		driver.close();
		}
}
