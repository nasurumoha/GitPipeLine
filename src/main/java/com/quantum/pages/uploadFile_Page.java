package com.quantum.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;


public class uploadFile_Page extends WebDriverBaseTestPage<WebDriverTestPage> {

	 public void accessURL()
	{
		 driver.get("https://altoconvertpdftojpg.com/");
	}

	public void uploadFile() throws InterruptedException, IOException {
		try{
			driver.findElement(By.xpath("//*[@id=\"browse\"]")).click();
			Thread.sleep(10000);
	        Runtime.getRuntime().exec("C:\\Users\\knasurudeen\\Documents\\fileUpload.exe");
	        Thread.sleep(10000);
	        System.out.println("The file has been uploaded");
		}
		catch(Exception e){
			Assert.fail("File not found");
		}
	}

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}
}