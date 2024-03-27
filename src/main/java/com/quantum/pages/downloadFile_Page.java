package com.quantum.pages;

import java.io.File;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;


public class downloadFile_Page extends WebDriverBaseTestPage<WebDriverTestPage> {
	
	
	public void accessURL()
	{
		driver.get("https://omayo.blogspot.com/p/page7.html");
	}

	public void downloadFile() throws InterruptedException {
	
	ChromeOptions options = new ChromeOptions();
//	EdgeOptions options = new EdgeOptions();
	String workspacePath = System.getProperty("user.dir");
    String downloadFilePath =workspacePath + "src\\main\\resources\\downloads";
//    System.out.println(workspacePath);
//    System.out.println(downloadFilePath);
    
    // Set Chrome preferences for download
	HashMap<String, Object> chromePrefs = new HashMap<>();
	chromePrefs.put("profile.default_content_settings.popups", 0);
	chromePrefs.put("download.default_directory", downloadFilePath);
	options.setExperimentalOption("prefs", chromePrefs);
	
	// Set Edge preferences for download
//	edgePrefs.put("download.default_directory", downloadFilePath);
	
	WebElement downloadLink = driver.findElement(By.xpath("//a[text()=\"ZIP file\"]"));
	
	Actions actions = new Actions(driver);
    actions.moveToElement(downloadLink).click().perform();
    
    try {
    	Thread.sleep(10000);
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    }
    
    File downloadedfile = new File(downloadFilePath + "\\DownloadDemo-master.zip");
    if(downloadedfile.exists()){
	   System.out.println("File downloaded successfully");
	   }
    else {
	   System.out.println("File downloaded unsuccessfully");
   }
  }

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}
}
