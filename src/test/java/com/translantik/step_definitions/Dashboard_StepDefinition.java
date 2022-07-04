package com.translantik.step_definitions;

import com.translantik.pages.DashboardPage;
import com.translantik.utilities.ConfigurationReader;
import com.translantik.utilities.Driver;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Dashboard_StepDefinition {
    DashboardPage dashboardPage = new DashboardPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @Then("user validates the page URL {string}")
    public void user_validates_the_page_url(String expectedURL) {
        String actualURL = Driver.getDriver().getCurrentUrl();
        expectedURL = ConfigurationReader.getProperty("web.Translantik.expectedURL");
        Assert.assertTrue(actualURL.contains(expectedURL));
    }

    @Then("user validates the page title {string}")
    public void user_validates_the_page_title(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        expectedTitle = ConfigurationReader.getProperty("web.Translantik.expectedTitle");
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @Then("user validates the Breadcrumb {string} is displayed")
    public void user_validates_the_breadcrumb_is_displayed(String expectedBreadCrumb) {
       // wait.until(ExpectedConditions.visibilityOf(dashboardPage.breadCrumb));
        String actualBreadCrumb = dashboardPage.breadCrumb.getText();
        System.out.println("actualBreadCrumb = " + actualBreadCrumb);
        System.out.println("expectedBreadCrumb = " + expectedBreadCrumb);
        Assert.assertTrue(actualBreadCrumb.contains(expectedBreadCrumb));
    }
}
