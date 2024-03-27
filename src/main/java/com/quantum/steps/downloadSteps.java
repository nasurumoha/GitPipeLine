package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.downloadFile_Page;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@QAFTestStepProvider
public class downloadSteps extends downloadFile_Page {

	@Given("user launch URL")
	public void user_launch_url(){
		accessURL();
	}
	@Then("click on help file to upload on project directory")
	public void click_on_help_file_to_upload_on_project_directory() throws InterruptedException {
	  downloadFile();
	}

}
