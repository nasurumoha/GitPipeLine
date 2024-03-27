package com.quantum.steps;

import java.io.IOException;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.uploadFile_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@QAFTestStepProvider
public class uploadSteps extends uploadFile_Page {

	@Given("user launch the url")
	public void user_launch_the_url() {
      accessURL();
	}

	@Then("click on chooseFiles button to upload the file")
	public void click_on_choose_files_button_to_upload_the_file() throws InterruptedException, IOException {
	uploadFile();
	}
}
