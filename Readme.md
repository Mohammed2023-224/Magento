## Introduction

 This is an automated testing project for a demo E-commerce website https://magento.softwaretestingboard.com/ .
 Automated some tests to check the folters and categories and an end to end test abouth the full process from navigating to the website then search and ordering the product 
 using parallel execution to execute on 3 browsers at the same time. number of tests are 6 for each browser

## Tools and Dependencies

 - Java (JDK 17)
 - Maven 3.9.6
 - Testng 7.4.0
 - Selenium 4.24.0
 - Other Dependencies and plugins can be found at the POM file

## How to run

- Can run from docker using docker-compose up (All the images should be downloaded directly)
  PS. To run this make sure that the setup file is configure to run remotely on port 4444 that is configured in the yaml file with the browsser is configured for all

- Can run locally by typing MVN CLEAN TEST in CMD
  PS. To run this make sure that the setup file is configured to run locally with the required browser or all for parallel execution cross browsers

## What can be enhanced

- Running of paralled tests can be enhanced as running only one test won't run for all browsers
- The waits strategy can be enhanced 
