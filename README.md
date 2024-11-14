This repository contains:
1.Setting up the QAF BDD Framework(Web/Mobile) and execute the task in local browser and Android Studio.

2.Implement upload(used **Auto IT** instead of robot class) and download functionality.

3.Add retry script for login/logout functionality, need to retry execution if the script fails for the first attempt. Max Attempt=3 and included the email functionality (if script fails consecutive for 3 times, then send email with report), if not script should not send the email with portal. Used **extent report** library instead of testng report.

4. Create data driven approach using JSON as the input file and output in JSON format
In Feature file mentioned the name of the application based on the name the system should need to check the property file and open the correct and from JSON the Username and Password should be
taken.
Created Config.property file and add two different application URLs
Created JSON file which contain Username /Password for both the applications
Read the config.property to get the application URLs to open the application
Read the Respective username/ Password according to the URL from JSON file

5.Created the data driver script to read the data from spreadsheet and fill the login application in Web and print the same in Console and separate spreadsheet for Output file.

