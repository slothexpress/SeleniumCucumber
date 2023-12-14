package stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

public class CreateAccountHappyPath {
    Random random = new Random();
    private int randomNumber = random.nextInt(9000);

    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("I am using browser {string}")
    public void iAmUsingBrowser(String browser) {
        if(browser.toUpperCase().equals("CHROME")) {
            driver = new ChromeDriver();

        }
        else if(browser.toUpperCase().equals("EDGE")) {
            driver = new EdgeDriver();
        }

        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @Given("I have filled in birthdate {string}")
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
        driver.findElement(By.id("member_emailaddress")).sendKeys("virus" + randomNumber + email);
    }

    @And("I have filled in emailConfirmation {string}")
    public void iHaveFilledInEmailConfirm(String emailConfirm) {
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys("virus" + randomNumber + emailConfirm);

    }

    @And("I have filled in password {string}")
    public void iHaveFilledInPassword(String password) {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys(password);
    }

    @And("I have filled in passwordConfirmation {string}")
    public void iHaveFilledInPasswordConfirm(String passwordConfirm) {
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys(passwordConfirm);
    }

    @And("I have checked the checkbox for Terms and Conditions")
    public void iHaveCheckedTheTermsAndConditionsCheckbox() {
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
    }

    @But("I have unchecked the checkbox for Terms and Conditions")
    public void iHaveUncheckedTheCheckboxForTermsAndConditions() {
        WebElement checkbox = driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box"));
        if(checkbox.isSelected()) {
            checkbox.click();
        }
    }

    @And("I have checked the checkbox for Age over eighteen")
    public void iHaveCheckedTheAgeIsOverCheckbox() {
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
    }

    @And("I have checked the checkbox for Ethics and Conduct")
    public void iHaveCheckedTheEthicsAndConductCheckbox() {
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
    }

    @When("I click Submit")
    public void iClickSubmit() {
        driver.findElement(By.name("join")).click();
    }

    @Then("I create an account and get {string}")
    public void iCreateAnAccount(String message) throws InterruptedException {
        Thread.sleep(2000);

        WebElement h2 = driver.findElement(By.tagName("h2"));
        String actual = h2.getText();
        String expected = message;

        Assert.assertEquals(expected, actual);

    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expected) throws InterruptedException {
        Thread.sleep(2000);

        WebElement span = driver.findElement(By.cssSelector("span[generated='true']"));

        String actual = span.getText();

        Assert.assertEquals(expected, actual);
    }

}
