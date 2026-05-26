package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager
{
    public static ExtentReports extentReports;

    //Extent reports responsible for managing overall report lifecycle
    public static ExtentReports getReportInstance() {
        if (extentReports == null) // Meaning: singleton-style report initialization
        //Meaning:create report object only once VERY important framework design pattern.
        //Otherwise :multiple report instances get created unnecessarily.
        {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html"); // meaning of ExtentSparkReporter is to Generate HTML report inside:test-output folder

            //sparkReporter is responsible for generating a beautiful HTML report
            sparkReporter.config().setReportName("API Automation Report");
            sparkReporter.config().setDocumentTitle("Test Execution Report");

            extentReports = new ExtentReports();

            extentReports.attachReporter(sparkReporter); //connects:report engine with HTML reporter UI

            extentReports.setSystemInfo("Framework", "RestAssured API Framework");
            extentReports.setSystemInfo("Tester", "Bhagyasri");
        }

        return extentReports;
    }
}
/*
 ITestListener:
 TestNG listener interface used for monitoring
 testcase execution lifecycle events automatically.
 */

/*
 ITestResult:
 Contains current testcase execution information.
Example:
 - testcase name
 - execution status
 - exception details
 */

/*
 flush():
 Finalizes and writes all report data into HTML report file.
 Important:
 Without flush(), report may not generate properly.
 */