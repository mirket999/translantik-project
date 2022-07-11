package com.translantik.pages;

import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage{
    public DashboardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy (id = "breadcrumb")
    public WebElement breadCrumb;

}
