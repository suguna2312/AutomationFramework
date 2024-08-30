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

public class PractiseTC_004 extends BaseClass{
	@Test
	public void tocreateORGwithType_001() throws EncryptedDocumentException, IOException, InterruptedException {
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getOrganizationLookUpImage().click();
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		Random r=new Random();
		int random = r.nextInt(1000);
		String ORGANIZATION = eutil.toReadDataFromExcelFile("Organization", 2, 2)+random;
		//String INDUSTRY = eutil.toReadDataFromExcelFile("Organization", 2, 3);
		String INDUSTRY = eutil.toReadDataFromExcelFile("Organization", 4, 3);
		//eutil.toReadDataFromExcelFile("Organization", random, random)
		String TYPE = eutil.toReadDataFromExcelFile("Organization", 2, 4);
		cop.getOrganizationName().sendKeys(ORGANIZATION);
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.toHandleDropdown(cop.getIndustrySelect(), INDUSTRY);
		//dropdown by visibleText???
		wutil.toHandleDropdown(INDUSTRY, cop.getIndustrySelect());
		//connot select dropdown by index????
		//wutil.toHandleDropdown(null, random);
		wutil.toHandleDropdown(cop.getTypeSelect(), TYPE);
		//to save and verify
		cop.getSaveButtonn().click();
		Thread.sleep(2000);
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String organization = oip.getHeaderElementOrganization().getText();
		String industry = oip.getIndustry().getText();
		String type = oip.getType().getText();
		if(organization.contains(ORGANIZATION)) {
			if(industry.contains(INDUSTRY)) {
				if(type.contains(TYPE)) {
					System.out.println(organization + " " + industry + " " + type + " created and TestScript passed");
				} else {
					System.out.println(type + " type selection---failed");
				}
			} else {
				System.out.println(industry + " industry creation---failed");
			}
		} else {
			System.out.println(organization + " creation---failed");
		}

		
		
		
		
		
		
		
		
	}

}
