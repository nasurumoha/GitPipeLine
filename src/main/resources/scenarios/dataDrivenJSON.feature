Feature: Login to different application using data driven approach using JSON as the input file and output in JSON format.

@DataDrivenJSON 
Scenario Outline: user able to create data driven approach using JSON as the input file and output in JSON format.
Given user able to login the "<application>" with valid credentials 

Examples:
|application|
|DemoSite| 
|DemoSitee|

