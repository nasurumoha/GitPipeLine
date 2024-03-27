package com.quantum.pages;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import excelReader.Xls_Reader;

public class DataDrivenExcel_Page extends WebDriverBaseTestPage<WebDriverTestPage> {

    public void loginExcel() throws IOException {

        driver.get("https://codenboxautomationlab.com/registration-form/");

        WebElement firstName = driver.findElement(By.name("fname"));
        WebElement lastName = driver.findElement(By.name("lname"));
        WebElement email = driver.findElement(By.name("email"));
        WebElement course = driver.findElement(By.id("nf-field-22"));
        Select dropdown = new Select(course);

        Xls_Reader reader = new Xls_Reader("src\\main\\resources\\data\\exceldata.xlsx");
        String sheetName = "Sheet1";

        int rowCount = reader.getRowCount(sheetName);

        XSSFWorkbook outputWorkbook = new XSSFWorkbook();
        XSSFSheet outputSheet = outputWorkbook.createSheet("Output");

        XSSFRow headerRow = outputSheet.createRow(0);
        headerRow.createCell(0).setCellValue("firstname");
        headerRow.createCell(1).setCellValue("lastname");
        headerRow.createCell(2).setCellValue("email");
        headerRow.createCell(3).setCellValue("SelecttheCourseyouwouldliketobook");
        headerRow.createCell(4).setCellValue("Howdoyouknowaboutus");
        headerRow.createCell(5).setCellValue("status");

        WebDriverWait wait = new WebDriverWait(driver, 10); // Initialize WebDriverWait with a 10-second timeout

        for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
            String fname = reader.getCellData(sheetName, "firstname", rowNum);
            String lname = reader.getCellData(sheetName, "lastname", rowNum);
            String emails = reader.getCellData(sheetName, "email", rowNum);
            String SelecttheCourse = reader.getCellData(sheetName, "SelecttheCourseyouwouldliketobook", rowNum);
            String Howdoyouknow = reader.getCellData(sheetName, "Howdoyouknowaboutus", rowNum);
            
         // Check if any of the required fields are empty, skip processing the row if empty
            if (fname.isEmpty() || lname.isEmpty() || emails.isEmpty()) {
                System.out.println("Skipping row " + rowNum + " as it has empty data.");
                continue;
            }

            System.out.println(fname + " " + lname + " " + emails + " " + SelecttheCourse + " " + Howdoyouknow);

            try {
                firstName.clear();
                firstName.sendKeys(fname);

                lastName.clear();
                lastName.sendKeys(lname);

                email.clear();
                email.sendKeys(emails);

                // Select the course only if it's not blank
                if (!SelecttheCourse.isBlank()) {
                    dropdown.selectByVisibleText(SelecttheCourse);
                }

                // Locate the 'knowAboutElement' using a stable XPath
                WebElement knowAboutElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nf-field-23-wrap\"]/div[2]/ul/li[\" + rowNum + \"]")));
                knowAboutElement.click();

                WebElement register = driver.findElement(By.id("nf-field-15"));
                register.click();
                
                Thread.sleep(10000);

                // Wait for registration success message
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), 'Your registration is completed.')]")));

                String status = "Pass";

                XSSFRow dataRow = outputSheet.createRow(rowNum - 1);
                dataRow.createCell(0).setCellValue(fname);
                dataRow.createCell(1).setCellValue(lname);
                dataRow.createCell(2).setCellValue(emails);
                dataRow.createCell(3).setCellValue(SelecttheCourse);
                dataRow.createCell(4).setCellValue(Howdoyouknow);
                dataRow.createCell(5).setCellValue(status);

                reader.setCellData(sheetName, "Status", rowNum, status);
            } catch (Exception e) {
                String status = "Fail: " + e.getMessage();
                XSSFRow dataRow = outputSheet.createRow(rowNum - 1);
                dataRow.createCell(0).setCellValue(fname);
                dataRow.createCell(1).setCellValue(lname);
                dataRow.createCell(2).setCellValue(emails);
                dataRow.createCell(3).setCellValue(SelecttheCourse);
                dataRow.createCell(4).setCellValue(Howdoyouknow);
                dataRow.createCell(5).setCellValue(status);

                reader.setCellData(sheetName, "Status", rowNum, status);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("OutputExcel.xlsx");
        outputWorkbook.write(outputStream);
        outputWorkbook.close();

        ((Closeable) reader).close();
    }

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}
}
