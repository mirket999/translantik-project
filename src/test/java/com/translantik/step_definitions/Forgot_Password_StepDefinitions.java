package com.translantik.step_definitions;

import com.translantik.pages.ForgotPasswordPage;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Forgot_Password_StepDefinitions {
    ForgotPasswordPage forgotpassword = new ForgotPasswordPage();
    @When("user clicks on {string} link")
    public void user_clicks_on_link(String link) {
        forgotpassword.forgot_your_password_link.click();
    }

    @When("user enters username {string} and clicks {string} button")
    public void userEntersUsernameAndClicksButton(String userName, String button) {
        forgotpassword.inputUsername.sendKeys(userName);
        forgotpassword.request_button.click();
    }

    @Then("user should change the password and shouldn't get error message {string}")
    public void userShouldChangeThePassword(String errorMessage) {
        String actualMessage = forgotpassword.alert_message.getText();
        System.out.println(actualMessage);
        System.out.println(errorMessage);
        Assert.assertFalse(actualMessage.equals(errorMessage));
    }
}
