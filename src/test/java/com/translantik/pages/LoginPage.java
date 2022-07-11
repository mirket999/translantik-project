package com.translantik.pages;

import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public String currentURL;


    @FindBy(id = "prependedInput")
    public WebElement inputUsername;

    @FindBy(id = "prependedInput2")
    public WebElement inputPassword;

    @FindBy(id = "_submit")
    public WebElement loginButton;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    public WebElement pageHeading;


    @FindBy(xpath = "//li[@id='user-menu']/a")
   // @FindBy(xpath = "//i[@class='fa-caret-down']")
    public WebElement toogle_dropdown_logout;

    @FindBy(linkText = "Logout")
    //@FindBy(xpath = "//a[@href='/user/logout']")
    public WebElement logout;

   @FindBy (xpath = "//div[@class='loader-mask shown']")
   public WebElement loader_mask;

    @FindBy (xpath = "//div[.='Invalid user name or password.']")
    public WebElement invalidUserNameOrPasswordMessage;

    @FindBy (xpath = "//input[@placeholder='Username or Email']")
    public WebElement placeholder_username;

    @FindBy (xpath = "//input[@placeholder='Password']")
    public WebElement placeholder_password;

    @FindBy (name = "_username")
    public WebElement validationMessage_InputUsername;

    @FindBy (name = "_password")
    public WebElement validationMessage_InputPassword;

    @FindBy (className = "custom-checkbox__icon")
    public WebElement remember_me_checkbox;

    @FindBy (className = "custom-checkbox__text")
    public WebElement remember_me_text;





    public String currentUrlAfterLogin_TranslantikPage(){
     Driver.getDriver().get(ConfigurationReader.getProperty("web.Translantik.URL"));
     inputUsername.sendKeys(ConfigurationReader.getProperty("username_translantik"));
     inputPassword.sendKeys(ConfigurationReader.getProperty("password_translantik"));
     loginButton.click();
     return Driver.getDriver().getCurrentUrl();
    }

}
