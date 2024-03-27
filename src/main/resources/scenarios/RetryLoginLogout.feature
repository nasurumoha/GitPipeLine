Feature: user able to retry execution for login/logout funcationality

@RetryLoginLogout
Scenario: Add Retry script for login/logout functionality, need to retry execution if the script fails for first attempt
Given user trying to login the application and if it fails then user retry login for max attempts
Then if script fails for consecutive times, then send email with report
