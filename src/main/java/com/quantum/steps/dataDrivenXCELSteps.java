package com.quantum.steps;

import java.io.IOException;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.DataDrivenExcel_Page;
import cucumber.api.java.en.Given;

@QAFTestStepProvider
public class dataDrivenXCELSteps extends DataDrivenExcel_Page {

	@Given("user able to login the application with valid credentials")
	public void user_able_to_login_the_application_with_valid_credentials() throws IOException, InterruptedException {
		loginExcel();
	}
}
