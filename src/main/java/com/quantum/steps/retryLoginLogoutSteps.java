package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.RetryLoginWithExtentReport;
import cucumber.api.java.en.*;

@QAFTestStepProvider
public class retryLoginLogoutSteps {

	RetryLoginWithExtentReport re = new RetryLoginWithExtentReport();
	
	@Given("user trying to login the application and if it fails then user retry login for max attempts")
	public void user_trying_to_login_the_application_and_if_it_fails_then_user_retry_login_for_max_attempts() throws InterruptedException {
		re.loginLogoutTest();
	}

	@Then("if script fails for consecutive times, then send email with report")
	public void if_script_fails_for_consecutive_times_then_send_email_with_report() {
	}
	
}
