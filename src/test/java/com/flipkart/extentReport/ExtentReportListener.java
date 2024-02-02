package com.flipkart.extentReport;

import com.flipkart.utilities.ReadConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class ExtentReportListener  implements IReporter {

    private static ExtentReports extent;
    public static  ReadConfig    readConfig= new ReadConfig();

    String timeStamp = new SimpleDateFormat("dd.MM.yyyy:HH-mm-ss").format(new Date());

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extent = new ExtentReports(outputDirectory + File.separator + "MyExtentReport-" + timeStamp + ".html", true);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }

        extent.flush();
        extent.close();
    }

    public void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
        extent.addSystemInfo("Test run by : ", "Sushant M");
        extent.addSystemInfo("Browser : ", readConfig.getBrsName());
        String path2 = System.getProperty("user.dir") + "/extent-config.xml";

        extent.loadConfig(new File(path2));
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());

                test.setStartedTime(getTime(result.getStartMillis()));
                test.setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                    test.log( status, "Test is: " + timeStamp +" : "+ status.toString().toLowerCase() + " : ");
                } else {
                    test.log( status, "My Custom Test : "   + timeStamp +" : " + status.toString().toLowerCase()+" : ");
                }

                extent.endTest(test);
            }
        }
    }




    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

}
///Users/sushant/.m2/repository/org/testng/testng/7.7.0/testng-7.7.0.jar