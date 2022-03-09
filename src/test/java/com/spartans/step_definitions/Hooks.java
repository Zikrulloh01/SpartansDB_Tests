package com.spartans.step_definitions;

import com.spartans.utilities.ConfigurationsReader;
import com.spartans.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {


    @Before
    public void setup(){
        Driver.getDriver().manage().window().maximize();
    }


    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()){
            final byte [] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        Driver.closeDriver();

    }
}
