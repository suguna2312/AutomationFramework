package testingPractice;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import vtiger.ObjectRepository.CreateOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationPage;
import vtiger.genericUtilities.BaseClass;
import vtiger.genericUtilities.ExcelFileUtility;
import vtiger.genericUtilities.WebDriverUtility;

public class PractiseTC_003 extends BaseClass{
	@Test
	public void testcase003() throws EncryptedDocumentException, IOException {
		
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getOrganizationLookUpImage().click();
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		Random r =new Random();
		int random = r.nextInt(1000);
		String ORGANIZATION = eutil.toReadDataFromExcelFile("Organization", 1, 2)+random;
		cop.getOrganizationName().sendKeys(ORGANIZATION);
		WebDriverUtility wutil=new WebDriverUtility();
		String INDUSTRY = eutil.toReadDataFromExcelFile("Organization", 1, 3);
		
		//Industry Dropdown Selection
		wutil.toHandleDropdown(cop.getIndustrySelect(), INDUSTRY);
		//save and verify
		cop.getSaveButtonn().click();
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String organization = oip.getHeaderElementOrganization().getText();
		String industry = oip.getIndustry().getText();
		if(organization.contains(ORGANIZATION)) {
			if(industry.contains(INDUSTRY)) {
				System.out.println(organization + " created with " + industry + " industry" + " and TestScript---passed");
			} else {
				System.out.println(industry + "industry creation---failed");
			}
		} else {
			System.out.println(organization + "---failed");
			}			
	}

	
	
		
		
		
		
		
	}


