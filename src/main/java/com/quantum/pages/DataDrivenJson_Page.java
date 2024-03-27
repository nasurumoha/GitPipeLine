package com.quantum.pages;

import java.io.FileReader;
import java.util.Properties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;

	public class DataDrivenJson_Page extends WebDriverBaseTestPage<WebDriverTestPage> {
		
	    public void logintoApplication(String sitename) { 

	    Properties properties = new Properties();

        try {
            // Load config.properties file
            properties.load(new FileReader("src\\main\\resources\\properties\\config.properties"));

            // Load JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src\\main\\resources\\data\\credentials.json"));
            JSONObject jsonObject = (JSONObject) obj;
            
            // Get the credentials array
            JSONArray credentialsArray = (JSONArray) jsonObject.get("credentials");
            
            // Iterate over credentials for each site
            for (Object cred : credentialsArray) {
                JSONObject credential = (JSONObject) cred;
                String site = (String) credential.get("site");
                String username = (String) credential.get("username");
                String password = (String) credential.get("password");

//                 Check if the site matches the desired site to execute
                if (site.equalsIgnoreCase("DemoSitee")) {
                	
                // Get the URL from config.properties based on the site
                String siteURL = properties.getProperty(site + "URL");
                if (siteURL != null) {
                    // Open the URL
                    driver.get(siteURL);

                    // Enter username and password
                    driver.findElement(By.name("username")).sendKeys(username);
                    driver.findElement(By.name("password")).sendKeys(password);
//                    driver.findElement(By.id("submit")).click();  // DemoSite
                    driver.findElement(By.xpath("//button[@type=\"submit\"]")).click(); // DemoSitee

                    // Dummy check for successful login
//                    boolean isLoggedIn = driver.findElement(By.xpath("//strong[text()=\"Congratulations student. You successfully logged in!\"]")).isDisplayed(); //DemoSite
                    boolean isLoggedIn = driver.findElement(By.xpath("//h4[contains(text(), 'Welcome')]")).isDisplayed(); //DemoSitee

                    // Output result in JSON format
                    JSONObject output = new JSONObject();
                    output.put("site", site);
                    output.put("username", username);
                    output.put("login_status", isLoggedIn ? "Success" : "Failed");
                    output.put("password", password);
                    
                    // Print output JSON
                    System.out.println(output.toJSONString());
               } else {
                    System.out.println("URL not found for site: " + site);
                } 
           }
       }
     }  
	    catch (Exception e) {
            e.printStackTrace();
        }
    }

		@Override
		protected void openPage(PageLocator locator, Object... args) {
			// TODO Auto-generated method stub
			
		}
}
