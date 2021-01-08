package com.kgisl.premium.runner;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json",jsonUsageReport="target/cucumber_usage.json" ,overviewReport = true, outputFolder = "target", detailedReport = true,featureOverviewChart = true,toPDF = true)
@CucumberOptions(features = "src\\test\\java\\com\\kgisl\\premium\\features",
glue = "com.kgisl.premium.behaviours",
plugin = {
		"html:target/cucumber-html-report", "json:target/cucumber.json", "pretty:target/cucumber-pretty.txt",
		"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"},
dryRun = false,
strict = true,
tags = {},
monochrome = true
)
public class RunCucumber extends AbstractTestNGCucumberTests{


}
