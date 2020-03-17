package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebshopTest {


    private WebDriver driver;


    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\diana\\workspace\\testautomation\\SeleniumMaven\\chromedriver.exe");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("network.proxy.http", "93.180.7.246");
        capabilities.setCapability("network.proxy.http_port", "8080");
        ChromeDriverService service = new ChromeDriverService.Builder().withWhitelistedIps("").withVerbose(true).build();
        driver = new ChromeDriver(service, capabilities);
    }


    @Test
    // User Storie 1
    // As a user, I want to be able to put goods in the customer basket, close the browser, open the browser and keep my goods
    public void products_added_to_the_cart_should_be_kept_if_you_leave_the_page() throws InterruptedException {

        // Go to the webshop
        driver.get("https://demo.prestashop.com/#/en/front");
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Since the actual webshop is within an iframe, we need to switch to that one
        driver.switchTo().frame("framelive");
        Thread.sleep(5000);

        // To wait for the element to be visible
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".featured-products .js-product-miniature"))));

        // Find all featured products
        List<WebElement> featuredProducts = driver.findElements(By.cssSelector(".featured-products .js-product-miniature"));

        // Click at the first featured product
        featuredProducts.get(0).click();

        // Assert that the cart has no products
        wait.until(ExpectedConditions.elementToBeClickable(By.className("cart-products-count")));
        Assert.assertEquals("(0)", driver.findElement(By.className("cart-products-count")).getText());

        // Click on button "Add to cart" to add the current product
        wait.until(ExpectedConditions.elementToBeClickable(By.className("add-to-cart")));
        driver.findElement(By.className("add-to-cart")).click();


        // Close the modal
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".modal-header button.close")));
        driver.findElement(By.cssSelector(".modal-header button.close")).click();

        wait.until(ExpectedConditions.textToBe(By.className("cart-products-count"), "(1)"));
        Assert.assertEquals("(1)", driver.findElement(By.className("cart-products-count")).getText());

        // Navigate from the webshop and then back so we can verify that the cookies keep our added products in cart
        driver.get("https://bbc.com");
        driver.get("https://demo.prestashop.com/#/en/front");

        // Since the actual webshop is within an iframe, we need to switch to that one
        driver.switchTo().frame("framelive");
        Thread.sleep(5000);

        Assert.assertEquals("(1)", driver.findElement(By.className("cart-products-count")).getText());

        driver.quit();
    }


    @Test
    public void dianas_andra_test() throws InterruptedException {

        // Go to the webshop
        driver.get("https://demo.prestashop.com/#/en/front");
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Since the actual webshop is within an iframe, we need to switch to that one
        driver.switchTo().frame("framelive");
        Thread.sleep(5000);

        // Assert that the cart has no products
        wait.until(ExpectedConditions.elementToBeClickable(By.className("cart-products-count")));
        Assert.assertEquals("(0)", driver.findElement(By.className("cart-products-count")).getText());

        driver.quit();
    }

    @Test
    // User Storie 2
    // As_a_user_I_want_to_be_able_to_come_up_with_options_when_using_the_search_box
    public void to_be_able_to_come_up_with_options_when_using_the_search_box() throws InterruptedException {
        // Go to the webshop
        driver.get("https://demo.prestashop.com/#/en/front");
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Since the actual webshop is within an iframe, we need to switch to that one
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();
        //Thread.sleep(5000);

        driver.findElement(By.className("ui-autocomplete-input")).sendKeys("t");
        driver.findElement(By.cssSelector("#search_widget > form > button > i")).click();

        //driver.findElement(By.cssSelector("placeholder=\"Search our catalog\"")).sendKeys("t");

        //driver.findElement(By.id("class=\"ui-autocomplete-input\"")).click();
        //driver.findElement(By.xpath("class=\"ui-helper-hidden-accessible\"")).click();

        //driver.findElement(By.id("class=\"col-md-10 col-sm-12 position-static\"")).click();
        //wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("action=\"//disastrous-sky.demo.prestashop.com/en/search\""))));
        //Assert.assertEquals("(T)", driver.findElement(By.className("placeholder=\"Search our catalog\"")).getText());

        //WebElement search = driver.findElement(By.className("t"));
        //search.click();

        //driver.findElement(By.cssSelector("class=header-top")).isDisplayed();

    }

    @After
    public void closeBrowser() {

    }

    @Test
    //User Storie 3.
    // As a user I want my personal data saved for the next time I need to buy something so I dont have to reenter them/come up automatically
    public void As_a_user_I_want_my_personal_data_saved_for_the_next_time_I_need_to_buy_something_so_I_dont_have_to_reenter_them__or_it_come_up_automatically() {
        // Go to the webshop
        driver.get("https://demo.prestashop.com/#/en/front");
        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Since the actual webshop is within an iframe, we need to switch to that one
        driver.switchTo().frame("framelive");
        driver.manage().window().maximize();

        // To wait for the element to be visible
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".featured-products .js-product-miniature"))));

        // Find all featured products
        List<WebElement> featuredProducts = driver.findElements(By.cssSelector(".featured-products .js-product-miniature"));

        // Click at the first featured product
        featuredProducts.get(0).click();

        // Assert that the cart has no products
        wait.until(ExpectedConditions.elementToBeClickable(By.className("cart-products-count")));
        Assert.assertEquals("(0)", driver.findElement(By.className("cart-products-count")).getText());

        // Click on button "Add to cart" to add the current product
        wait.until(ExpectedConditions.elementToBeClickable(By.className("add-to-cart")));
        driver.findElement(By.className("add-to-cart")).click();

        //driver.findElement(By.className("class=material-icons rtl-no-flip")).sendKeys();
        // Close the modal
       // wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("Proceed to checkout")));
        //driver.findElement(By.cssSelector("Proceed to checkout")).click();

        //driver.findElement(By.cssSelector("href=//spurious-pull.demo.prestashop.com/en/cart?action=show")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("href=//spurious-pull.demo.prestashop.com/en/cart?action=show")));
        driver.findElement(By.cssSelector("Proceed to checkout")).click();

    }
}
