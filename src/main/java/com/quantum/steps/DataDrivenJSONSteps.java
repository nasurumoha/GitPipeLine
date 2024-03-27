package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.DataDrivenJson_Page;
import cucumber.api.java.en.Given;

@QAFTestStepProvider
public class DataDrivenJSONSteps extends DataDrivenJson_Page{

	@Given("user able to login the \"([^\"]*)\" with valid credentials")
	public void user_able_to_login_the_with_valid_credentials(String string) {
		logintoApplication(string);
	}
}
