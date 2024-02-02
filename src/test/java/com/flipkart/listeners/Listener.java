package com.flipkart.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class Listener implements ITestListener
{

    public void onTestStart(ITestResult Result) {

        System.out.println(Result.getName() +" : Test Started");
    }

    public void onTestSuccess(ITestResult Result) {

        System.out.println(Result.getName() +" : Test Successfully Done");


    }

    public void onTestFailure(ITestResult Result) {
        System.out.println(Result.getName() +" : Test Failed");

    }

    public void onTestSkipped(ITestResult Result) {
        System.out.println(Result.getName() +" : Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {
        System.out.println(Result.getName() +" : Test Failed but with Success Percentage");
    }

    public void onStart(ITestContext Result) {
        System.out.println(Result.getName() +" : Started");
    }

    public void onFinish(ITestContext Result) {
        System.out.println(Result.getName() +" : Test Finished");
    }
}
