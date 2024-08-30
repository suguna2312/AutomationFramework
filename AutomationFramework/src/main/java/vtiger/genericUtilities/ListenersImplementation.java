package vtiger.genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	ExtentReports report;
	ExtentTest test ;

	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Started");
		//for reports
		test = report.createTest(methodname);

	}

	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Passed");
		//for reports
		test.log(Status.PASS, methodname+"--Passed");
	}

	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Failed");
		//for reports
		test.log(Status.FAIL, methodname+"--Failed");
		
		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		String screenshotname = methodname + " " + jutil.toGetSystemDateInFormat();
		try {
			String path = wutil.toTakeScreenshot(BaseClass.sDriver, screenshotname);
			//for reports
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname + "---Skipped");
		//for reports
		test.log(Status.SKIP, methodname+"--Skipped");
	}

	public void onStart(ITestContext context) {

		System.out.println("---Suite execution started");
		//for reports
		ExtentSparkReporter htmlreport = new ExtentSparkReporter(
				"./ExtentReports/Report-" + new JavaUtility().toGetSystemDateInFormat() + ".html");
		htmlreport.config().setDocumentTitle("Vtiger Execution Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("VTIGER EXECUTION REPORT");

		report = new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base Url", "http://localhost:8888/");
		report.setSystemInfo("Username", "admin");
		report.setSystemInfo("Password", "password");
		report.setSystemInfo("Base Browser", "Chrome");
	}

	public void onFinish(ITestContext context) {

		System.out.println("---Suite execution Finished");
		//for reports
		report.flush();
	}

}
