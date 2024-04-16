package com.quantum.pages;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;


public class RetryLoginWithExtentReport extends WebDriverBaseTestPage<WebDriverTestPage> {

	@FindBy(locator = "username.Field")
    private static QAFWebElement usersname;
	@FindBy(locator = "password.Field")
    private static QAFWebElement passsword;
	@FindBy(locator = "submit.Button")
    private static QAFWebElement submit;
	@FindBy(locator = "verify.Message")
    private static QAFWebElement verifyyMessage;
	@FindBy(locator = "logout.Field")
    private static QAFWebElement logout;
	
	
	    private static final String USERNAME = "stuent";
	    private static final String PASSWORD = "Password123";
	    private static final int MAX_ATTEMPTS = 3;
	    
	    private static ExtentReports extent;
	    private static ExtentTest logger;
	    
	    public void loginLogoutTest() throws InterruptedException {
	    	initializeExtentReport();
	        int attempts = 0;
	        boolean loginSuccess = false;  

	        while (attempts < MAX_ATTEMPTS && !loginSuccess) {
	            attempts++;
	            if (!login(USERNAME, PASSWORD)) {
	            	verifyAndGenerateReport();
	            	logout();
	                loginSuccess = true;
	            } else {
	            	System.out.println("Login attempt " + attempts + " failed.");
	            }
	        }
	        if (!loginSuccess) {
	            String report = "Login failed for " + MAX_ATTEMPTS + " consecutive attempts.";
	            System.out.println("Login failed for " + attempts + " consecutive attempts.");
	            logger.log(Status.FAIL, report);
	            extent.flush();
	            sendEmailWithReport();
	        }
	        	 extent.flush();
	    }

	    public static void initializeExtentReport() {
	        extent = new ExtentReports();
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("ExtentReport.html");
	        extent.attachReporter(htmlReporter);
	        logger = extent.createTest("Login Test");
	    }

	    public static boolean login(String username, String password) throws InterruptedException {
	    	new WebDriverTestBase().getDriver().get("https://practicetestautomation.com/practice-test-login/");
	    	usersname.sendKeys(USERNAME);
	    	passsword.sendKeys(PASSWORD);
	        submit.click();
	        Thread.sleep(1000);
	        logger.log(Status.INFO, "Attempting login with username: " + username);
	        String verifyMessage = "Congratulations student. You successfully logged in!";
	        if (verifyMessage.equals("Congratulations student. You successfully logged in!")) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public static void verifyAndGenerateReport() {
	       logger.log(Status.INFO, "Verifying homepage");
	       boolean loginSuccess = false;
	       if (!loginSuccess) {
	       String verifyMessage = "Congratulations student. You successfully logged in!";
	       verifyyMessage.getAttribute(verifyMessage);
	       System.out.println("The verification of homepage is successful");
	       Assert.assertEquals(verifyMessage,"Congratulations student. You successfully logged in!");
	       logger.log(Status.PASS, "Login and homepage verification successful.");
	       }    
}

	    public static void logout() {
	        logout.click();
	        logger.log(Status.INFO, "Logging out");
	    }

	    public static void sendEmailWithReport() {
	        final String from = "mdnasurudeen456@gmail.com";
	        final String password = "odvr ebee yzhr jttw";
	        final String to = "nasurudeenmohamed3@gmail.com";

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "25"); 

	        Session session = Session.getInstance(props,
	                new Authenticator() {
	                    @Override
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(from, password);
	                    }
	                });

	        try {
	            Message emailMessage = new MimeMessage(session);
	            emailMessage.setFrom(new InternetAddress(from));
	            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	            emailMessage.setSubject("Automation Report");

	            // Create the email body
	            Multipart multipart = new MimeMultipart();
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            messageBodyPart.setText("Please find attached automation report.");

	            // Attach Extent Report file
	            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	            attachmentBodyPart.attachFile(new File("ExtentReport.html"));

	            // Add parts to the email body
	            multipart.addBodyPart(messageBodyPart);
	            multipart.addBodyPart(attachmentBodyPart);

	            emailMessage.setContent(multipart);

	            // Send email
	            Transport.send(emailMessage);

	            System.out.println("Email sent successfully.");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

		@Override
		protected void openPage(PageLocator locator, Object... args) {
			// TODO Auto-generated method stub
			
		}
	}

