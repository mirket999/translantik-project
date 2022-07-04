package com.translantik.step_definitions;

import com.translantik.pages.LoginPage;
import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @When("user enters {string} and {string} and logins")
    public void user_enters_and(String username, String password) {
        loginPage.inputUsername.sendKeys(username);
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Then("user {string} lands on the {string}")
    public void user_lands_on_the(String usertype, String expectedPageHeading) {
        wait.until(ExpectedConditions.visibilityOf(loginPage.pageHeading));
        String actualPageHeading = loginPage.pageHeading.getText();
        System.out.println("actualPageHeading = " + actualPageHeading);
        Assert.assertTrue("Actual and expected page headings don't match", actualPageHeading.contains(expectedPageHeading));
    }


    @Given("user is on the translantik login page")
    public void userIsOnTheTranslantikLoginPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("web.Translantik.URL"));


    }
}
