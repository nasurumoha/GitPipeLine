package com.quantum.pages;

import java.io.FileReader;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;

public class DataDrivenJSON extends WebDriverBaseTestPage<WebDriverTestPage> {
    
    public void logintoApplications(String scenarioName) { 
        try {
            // Load the environment and JSON file
            EnvUtils.switchEnvironment(EnvUtils.props.getProperty("Environment"));

            // Get the scenario data
            JSONObject scenarioData = EnvUtils.getScenario(scenarioName);
            String username = (String) scenarioData.get("username");
            String password = (String) scenarioData.get("password");
            
            // Get the URL from config.properties based on the environment
            String siteURL = EnvUtils.getUrl();

            if (siteURL != null) {
                // Open the URL
                driver.get(siteURL);

                // Enter username and password
                driver.findElement(By.name("username")).sendKeys(username);
                driver.findElement(By.name("password")).sendKeys(password);
                
//                driver.findElement(By.id("submit")).click();  // DemoSite
                driver.findElement(By.xpath("//button[@type=\"submit\"]")).click(); // DemoSitee

                // Check for successful login
//                boolean isLoggedIn = driver.findElement(By.xpath("//strong[text()=\"Congratulations student. You successfully logged in!\"]")).isDisplayed(); //DemoSite
                boolean isLoggedIn = driver.findElement(By.xpath("//h4[contains(text(), 'Welcome')]")).isDisplayed(); //DemoSitee

                // Output result in JSON format
                JSONObject output = new JSONObject();
                output.put("scenario", scenarioName);
                output.put("username", username);
                output.put("login_status", isLoggedIn ? "Success" : "Failed");
                output.put("password", password);
                
                // Print output JSON
                System.out.println(output.toJSONString());
            } else {
                System.out.println("URL not found for environment: " + EnvUtils.props.getProperty("Environment"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void openPage(PageLocator locator, Object... args) {
        // TODO Auto-generated method stub
    }
}

