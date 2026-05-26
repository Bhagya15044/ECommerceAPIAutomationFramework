package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import reports.ExtentReportManager;

public class TestListener implements ITestListener
{
    ExtentReports extentReports = ExtentReportManager.getReportInstance();
    ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result)
    {
        extentTest = extentReports.createTest(result.getName());
        System.out.println("TestCase Started : " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        extentTest.pass("Test Passed");
        System.out.println("TestCase Success : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        extentTest.fail("Test Failed");
        System.out.println("TestCase Failure : " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        extentTest.skip("Test Skipped");
        System.out.println("TestCase Skipped : " + result.getName());
    }
}
/*
 ExtentReports:
 Main report engine used for managing overall report lifecycle.

 Responsibilities:
 - create report
 - manage report execution flow
 - write report data into HTML file
 */

/*
 ExtentTest:
 Represents individual testcase entry inside Extent Report.

 Example:
 - CreateUserTest
 - UpdateUserTest
 - DeleteUserTest

 Used for updating testcase status like:
 - pass
 - fail
 - skip
 */

/*
 ExtentReportManager:
 Centralized reusable report configuration manager.

 Responsibilities:
 - initialize Extent Report
 - configure report path
 - configure HTML reporter
 - provide reusable report instance
 */

/*
 getReportInstance():
 Returns reusable singleton-style Extent Report object.
 Why:
 To avoid creating multiple report instances during execution.
 */

/*
 createTest():
 Creates new testcase entry inside HTML report dynamically
 based on currently executing testcase name.
 */
