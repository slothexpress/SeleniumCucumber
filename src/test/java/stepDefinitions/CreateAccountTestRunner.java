package stepDefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAccountTestRunner {

    Random random;
    private int randomNumber;
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        random = new Random();
        randomNumber = random.nextInt(9000);
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }

    public void helperWaitForElement(WebDriver driver, String cssSelector) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    public void helperGetURL(WebDriver driver) {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @Given("I am using browser Chrome")
    public void iAmUsingBrowserChrome() {
        driver = new ChromeDriver();
        helperGetURL(driver);
    }

    @Given("I am using browser Edge")
    public void iAmUsingBrowserEdge() {
        driver = new EdgeDriver();
        helperGetURL(driver);
    }

    @And("I have filled in birthdate {string}")
    public void iHaveFilledInBirthdate(String birthdate) {
        driver.findElement(By.id("dp")).sendKeys(birthdate);
    }

    @And("I have filled in first name {string}")
    public void iHaveFilledInFirstName(String firstName) {
        driver.findElement(By.id("member_firstname")).sendKeys(firstName);

    }

    @And("I have filled in last name {string}")
    public void iHaveFilledInLastName(String lastName) {
        driver.findElement(By.id("member_lastname")).sendKeys(lastName);

    }

    @And("I have filled in email {string}")
    public void iHaveFilledInEmail(String email) {
        driver.findElement(By.id("member_emailaddress"))
                .sendKeys("virus" + randomNumber + email);
    }

    @And("I have filled in emailConfirmation {string}")
    public void iHaveFilledInEmailConfirm(String emailConfirm) {
        driver.findElement(By.id("member_confirmemailaddress"))
                .sendKeys("virus" + randomNumber + emailConfirm);

    }

    @And("I have filled in password {string}")
    public void iHaveFilledInPassword(String password) {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
    }

    @And("I have filled in passwordConfirmation {string}")
    public void iHaveFilledInPasswordConfirm(String passwordConfirm) {
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(passwordConfirm);
    }


    @And("I have checked the checkbox for Terms and Conditions {string}")
    public void iHaveCheckedTheCheckboxForTermsAndConditions(String check) {
        if(check.equals("true")) {
            driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        }
    }


    @And("I have checked the checkbox for Age over eighteen {string}")
    public void iHaveCheckedTheCheckboxForAgeOverEighteen(String check) {
        if(check.equals("true")) {
            driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        }
    }
    @And("I have checked the checkbox for <Age over eighteen>")
    public void iHaveCheckedTheCheckboxForAgeOverEighteen(boolean check) {
        if(check) {
            driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        }
    }

    @And("I have checked the checkbox for Ethics and Conduct {string}")
    public void iHaveCheckedTheCheckboxForEthicsAndConduct(String check) {
        if(check.equals("true")) {
            driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
        }
    }

    @When("I click Submit")
    public void iClickSubmit() {
        driver.findElement(By.name("join")).click();
    }

    @Then("I verify status success and get message {string}")
    public void iVerifyStatusSuccessAndGetMessage(String message) throws InterruptedException {
        Thread.sleep(2000);

        WebElement h2 = driver.findElement(By.tagName("h2"));
        String actual = h2.getText();
        String expected = message;

        assertEquals(expected, actual);
    }

    @Then("I verify status error and get message {string}")
    public void iVerifyStatusErrorAndGetMessage(String message) throws InterruptedException {
        String cssSelector = "span[generated='true']";
        String expected = message;

        helperWaitForElement(driver, cssSelector);
        WebElement span = driver.findElement(By.cssSelector(cssSelector));

        String actual = span.getText();

        assertEquals(expected, actual);
    }

}
