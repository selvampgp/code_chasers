package com.kgisl.version.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"classpath:features"},
		glue="com.kgisl.version.stepdefinitions",
		plugin= {"html:target/cucumber-html-report",
				  "json:target/cucumber.json",
				  "pretty:target/cucumber-pretty.txt",
				  "usage:target/cucumber-usage.json",
				  "junit:target/cucumber-results.xml",
				  "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm"},
		tags= {"@UserLicense"},
		monochrome=true,//to remove the unreadable character from result
        dryRun=false,//the function is missed in the Step Definition for any Step in Feature File, it will give us the message
        strict=false//if you want to skip undefined steps from execution
		)

public class RunnerClass {
		public static void main(String []a) {
			
		}
}
