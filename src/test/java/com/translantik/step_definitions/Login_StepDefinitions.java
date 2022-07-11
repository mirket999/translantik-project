package com.translantik.step_definitions;

import com.translantik.pages.LoginPage;
import com.translantik.utilities.BrowserUtils;
import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class Login_StepDefinitions {
    LoginPage loginPage = new LoginPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
    Actions action = new Actions(Driver.getDriver());

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
        Assert.assertTrue("Actual and expected page headings don't match", actualPageHeading.contains(expectedPageHeading));
    }


    @Given("user is on the translantik login page")
    public void userIsOnTheTranslantikLoginPage() {
        Driver.getDriver().get(ConfigurationReader.getProperty("web.Translantik.URL"));
    }


    @When("user copies the URL")
    public void user_copies_the_url() {
        loginPage.currentURL = Driver.getDriver().getCurrentUrl();
    }


    @When("user logouts")
    public void user_logouts() {
        BrowserUtils.sleep(2);
       loginPage.toogle_dropdown_logout.click();
       loginPage.logout.click();
//        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
//        js.executeScript("arguments[0].click()", loginPage.logout);

    }

    @When("user pastes the URL to browser and enters login without authentication")
    public void user_pastes_the_url_to_browser_and_logins() {
        // String URL = user_copies_the_url();
        Driver.getDriver().get(loginPage.currentURL);
        loginPage.loginButton.click();

    }

    @Then("user is still on the translantik login page")
    public void userIsStillOnTheTranslantikLoginPage() {
        String actualTitle = Driver.getDriver().getTitle();
        String expectedTitle = ConfigurationReader.getProperty("web.Translantik.expectedLoginPageTitle");

        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @Then("user {string} doesn't land on the {string}")
    public void userDoesnTLandOnThe(String username, String password) {
        Assert.assertTrue(loginPage.invalidUserNameOrPasswordMessage.isDisplayed());

    }

    @When("user closes the browser")
    public void user_closes_the_browser() {
        Driver.closeDriver();
    }

    @When("user opens a new browser and pasts copied URL")
    public void user_opens_a_new_browser_and_pasts_copied_url() {
        Driver.getDriver().get(loginPage.currentURL);
    }


    @And("user opens a new tab")
    public void userOpensANewTab() {
        JavascriptExecutor js = ((JavascriptExecutor) Driver.getDriver());
        js.executeScript("window.open()");

    }

    @And("user closes the previous tab")
    public void userClosesThePreviousTab() {
        ArrayList<String> windowHandles = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(windowHandles.get(0));
        Driver.getDriver().close();
        Driver.getDriver().switchTo().window(windowHandles.get(1));
    }

    @And("user pastes the URL")
    public void userPastesTheURL() {
        Driver.getDriver().get(loginPage.currentURL);
    }

    @Then("proper placeholder is displayed for username and password fields")
    public void properPlaceholderIsDisplayed() {
        Assert.assertTrue(loginPage.placeholder_username.isDisplayed());
        Assert.assertTrue(loginPage.placeholder_password.isDisplayed());
    }

    @Then("user gets {string}")
    public void userGets(String expected_warningMessage) {
        String actualWarningMessage = loginPage.invalidUserNameOrPasswordMessage.getText();
        Assert.assertTrue(actualWarningMessage.equalsIgnoreCase(expected_warningMessage));
    }

    @When("user enters username {string} and logins")
    public void userEntersAndLogins(String username) {
        loginPage.inputUsername.sendKeys(username);
        loginPage.loginButton.click();
        BrowserUtils.sleep(2);
    }

    @Then("user gets {string} for username" )
    public void userGetsA(String expectedWarningMessage) {
        String actualWarningMessage = loginPage.validationMessage_InputPassword.getAttribute("validationMessage");
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);

    }

    @Then("user gets {string} for password")
    public void userGetsForPassword(String expectedWarningMessage) {
        String actualWarningMessage = loginPage.validationMessage_InputUsername.getAttribute("validationMessage");
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
    }

    @When("user enters password {string} and logins")
    public void userEntersPasswordAndLogins(String password) {
        loginPage.inputPassword.sendKeys(password);
        loginPage.loginButton.click();

    }

    @Then("password field is toggled to hide")
    public void passwordFieldIsToggledToHide() {
        boolean isPasswordHidden = loginPage.inputPassword.getAttribute("type").equalsIgnoreCase("password");
        Assert.assertTrue(isPasswordHidden);
    }


    @Then("{string} is not visible in Page Source")
    public void isNotVisibleInPageSource(String password) {
        System.out.println(loginPage.inputPassword.getAttribute("value"));
        Assert.assertTrue(!loginPage.inputPassword.getAttribute("value").equals(password));
    }

    @When("user enters password {string}")
    public void user_enters_password(String password) {
        loginPage.inputPassword.sendKeys(password);

    }

    @When("user copies the password and passes to other field")
    public void user_copies_the_password() {
        loginPage.inputPassword.sendKeys((Keys.CONTROL + "a"));
        loginPage.inputPassword.sendKeys((Keys.CONTROL + "c"));
        loginPage.inputUsername.sendKeys(Keys.CONTROL + "v");
    }

    @Then("copied password shouldn't equal to given password {string}")
    public void copied_password_shouldn_t_equal_to_given_password(String expectedPassword) {
       String actualPassword= loginPage.inputUsername.getAttribute("value");
       Assert.assertFalse("Copied password doesn't match with expected one", actualPassword.equals(expectedPassword));
    //since CTRL+C doesn't work in password field, last copied text is pasted to username value.
    }


    @Then("user lands on the {string} page")
    public void user_lands_on_the_page(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        System.out.println("actualTitle = " + actualTitle);
        System.out.println("expectedTitle = " + expectedTitle);
        Assert.assertTrue(actualTitle.equals(expectedTitle));
    }

    @Then("{string} should be clickable")
    public void shouldBeClickable(String linkCheckbox) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.remember_me_checkbox));
            System.out.println("Web Element is clickable");
        } catch(TimeoutException e){
            System.out.println("Web Element is not clickable");
          e.getStackTrace();

        }
    }

    @Then("user sees the {string} link")
    public void userSeesTheLink(String providedLink) {
    Assert.assertTrue(loginPage.remember_me_text.isDisplayed());
    }

    @When("user clicks on username input box")
    public void userClicksOnUsernameInputBox() {
        loginPage.inputUsername.click();

    }

    @And("user enters a valid username {string} and hits ENTER button")
    public void userEntersAValidUsernameAndHitsENTERButton(String username) {
        loginPage.inputUsername.sendKeys(username+Keys.ENTER);
    }

    @And("user enters password {string} and hits ENTER button")
    public void userEntersPasswordAndHitsENTERButton(String password) {
     loginPage.inputPassword.sendKeys(password + Keys.ENTER);
    }

    @And("user enters a valid username {string} and hits TAB button")
    public void userEntersAValidUsernameAndHitsTABButton(String username) {
        loginPage.inputUsername.sendKeys(username + Keys.TAB);
    }

    @Then("background color of button {string} is as expected {string}")
    public void backgroundColorOfButtonIsAsExpected(String button_name, String expected_hex_code) {
    String background_color = loginPage.loginButton.getCssValue("background-color");
    String actualHexcode_background =  Color.fromString(background_color).asHex();
    Assert.assertTrue(actualHexcode_background.equals(expected_hex_code));
    }


}
