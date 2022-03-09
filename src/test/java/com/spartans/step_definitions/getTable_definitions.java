package com.spartans.step_definitions;

import com.spartans.pages.HomePage;
import com.spartans.utilities.BrowserUtils;
import com.spartans.utilities.ConfigurationsReader;
import com.spartans.utilities.DBUtils;
import com.spartans.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;


import java.util.List;
import java.util.Map;
import java.util.Random;

public class getTable_definitions {



    @Given("user is on home page")
    public void user_is_on_home_page() {
        Driver.getDriver().get(ConfigurationsReader.get("url"));
    }


    @When("user adds a new person")
    public void userAddsANewPerson(Map<String, String> details) {
        new HomePage().addNewPerson(details.get("name"), details.get("sex"), details.get("phone"));
    }

    @Then("Verify num of people match with database")
    public void verifyNumOfPeopleMatchWithDatabase() {
        DBUtils.createConnection();
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select count(*) as total from spartans");
        String totalText = new HomePage().total.getText();
        String actualTotal = queryResultMap.get(0).get("TOTAL").toString();
        Assert.assertTrue(totalText.contains(actualTotal));
        DBUtils.destroy();
    }

    @When("User clicks edit button")
    public void userClicksEditButton() {
        new HomePage().getRandomSpartan().click();
    }

    @And("Updates the spartan phone number, It should match with database")
    public void updatesTheSpartanPhoneNumber() {

        String changedNumber = new HomePage().updatePhoneNumber();
        new HomePage().homeLink.click();
        BrowserUtils.waitFor(5);

        DBUtils.createConnection();

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap("select SPARTAN_ID as id from SPARTANS\n" +
                "where PHONE='" + changedNumber + "'");


        String phoneLocator="(//td[.='" + queryResultMap.get(0).get("ID").toString()+ "'])/following-sibling::td[2]";

        String text = Driver.getDriver().findElement(By.xpath(phoneLocator)).getText();

        Assert.assertEquals(changedNumber,text);


        DBUtils.destroy();

    }


    @When("user clicks delete button")
    public void userClicksDeleteButton() {
        new HomePage().deleteSpartan();
    }
}
