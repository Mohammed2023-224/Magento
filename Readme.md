## Introduction

 This is an automated testing project for a demo E-commerce website https://magento.softwaretestingboard.com/ .
 Automated some tests to check the filters and categories and an end-to-end test about the full process from navigating to the website and searching and ordering the product 
 using parallel execution to execute on 3 browsers simultaneously. number of tests is 6 for each browser

## Tools and Dependencies

 - Java (JDK 17)
 - Maven 3.9.6
 - Testng 7.4.0
 - Selenium 4.24.0
 - Other Dependencies and plugins can be found in the POM file

## How to run

- Can run from docker using docker-compose up (All the images should be downloaded directly)
  
  PS. To run this make sure that the setup file is configured to run remotely on port 4444 configured in the YAML file with the browser configured for all browsers

- Can run locally by typing MVN CLEAN TEST in CMD
  
  PS. To run this make sure that the setup file is configured to run locally with the required browser or all for parallel execution across browsers

## What can be enhanced

- Running of parallel tests can be enhanced as running only one test won't run for all browsers
- The wait strategy can be enhanced
- Setup file configuration logic can be adjusted
- Using JSON instead of Excel
- Handle more browsrs
