package com.translantik.pages;

import com.translantik.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    public ForgotPasswordPage(){
            PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(linkText = "Forgot your password?")
    public WebElement forgot_your_password_link;

    @FindBy(id = "prependedInput")
    public WebElement inputUsername;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement request_button;


    @FindBy (xpath = "//div[@class='alert alert-warn']")
    public WebElement alert_message;
}
