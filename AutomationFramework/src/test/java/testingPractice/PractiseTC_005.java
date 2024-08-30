package testingPractice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactOrganizationLinkPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateContactPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.genericUtilities.BaseClass;
import vtiger.genericUtilities.ExcelFileUtility;
import vtiger.genericUtilities.WebDriverUtility;

public class PractiseTC_005 extends BaseClass{
	
	@Test
	public void toCreateContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {
		
		
		HomePage hp=new HomePage(driver);
		hp.getContactsLink().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getContactLookUpImage().click();
		CreateContactPage ccp=new CreateContactPage(driver);
		ExcelFileUtility eutil=new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		ccp.getLastname().sendKeys(LASTNAME);
		//To click on Organization lookUp image(+)
		ccp.getOrganizationLookUpImageInCreateContact().click();
		//Organization link page
		ContactOrganizationLinkPage cop=new ContactOrganizationLinkPage(driver);
		WebDriverUtility wutil=new WebDriverUtility();
		String ORGANIZATION_HEADER = eutil.toReadDataFromExcelFile("Contacts", 1, 3);
		
		wutil.toswitchWindowWithWebElementAddress(driver, cop.getOrganizationLinkHeader(), ORGANIZATION_HEADER);
		
		Thread.sleep(2000);
		cop.getOrganizationNameLink().click();
		Thread.sleep(2000);
		String HOMEPARTIALTITLE = eutil.toReadDataFromExcelFile("Contacts", 1, 3);
		wutil.toswitchWindow(driver, HOMEPARTIALTITLE);
		
		//To Save and Verify
		ccp.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getHeaderElement().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "--passed");
		} else {
			System.out.println(lastname + "--failed");
		}
		
		
		
		
			
			
			
	}
	
	
	
	
	
	
	

}
