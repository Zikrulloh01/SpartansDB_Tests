package com.spartans.pages;

import com.github.javafaker.Faker;
import com.google.common.primitives.Chars;
import com.spartans.utilities.BrowserUtils;
import com.spartans.utilities.Driver;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.ShuffleOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.*;

public class HomePage extends BasePage{


    @FindBy(css = "#add_spartan_btn")
    public WebElement addButton;

    @FindBy(css = "#name")
    public WebElement nameInput;

    @FindBy(css = "#genderSelect")
    public WebElement genderDropdown;

    @FindBy(css = "#phone")
    public WebElement phoneInput;

    @FindBy(css = "#submit_btn")
    public WebElement submitButton;

    @FindBy(linkText = "Back to the List")
    public WebElement homeLink;

    @FindBy(css = "#total")
    public WebElement total;
    @FindBy(css = ".btn.btn-primary")
    public WebElement updateButton;


    public void addNewPerson(String name, String gender, String phoneNum){

        addButton.click();
        nameInput.sendKeys(name);
        getGenderSelect().selectByValue(gender);
        phoneInput.clear();
        phoneInput.sendKeys(phoneNum);
        submitButton.click();
        homeLink.click();

    }

    public Select getGenderSelect(){
        Select select = new Select(genderDropdown);
        return select;
    }



    public WebElement getRandomSpartan(){
        Random random = new Random();
        int randomId= random.nextInt(100);
        String spartanEditLocator = "#edit_spartan_" + randomId;
        return Driver.getDriver().findElement(By.cssSelector(spartanEditLocator));
    }


    public String updatePhoneNumber(){
        Faker faker = new Faker();
        phoneInput.clear();
        String number = faker.numerify("##########");
        phoneInput.sendKeys(number);
        updateButton.click();
        return number;
    }


    public void deleteSpartan(){
        Random random = new Random();
        int randomId= random.nextInt(100);
        String deleteLocator = "#delete_spartan_" + randomId;
        Driver.getDriver().findElement(By.cssSelector(deleteLocator)).click();
    }


}
