package pages;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.Waiter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterPage {
    private WebDriver driver;
    private By firstnameInput = By.id("AccountFrm_firstname");
    private By lastnameInput = By.id("AccountFrm_lastname");
    private By emailInput = By.id("AccountFrm_email");
    private By address1Input = By.id("AccountFrm_address_1");
    private By cityInput = By.id("AccountFrm_city");
    private By regionInput = By.id("AccountFrm_zone_id");
    private By postcodeInput = By.id("AccountFrm_postcode");
    private By countryInput = By.id("AccountFrm_country_id");
    private By loginNameInput = By.id("AccountFrm_loginname");
    private By passwordInput = By.id("AccountFrm_password");
    private By confirmPasswordInput = By.id("AccountFrm_confirm");
    private By privacyPolicyBtn = By.id("AccountFrm_agree");
    private By errorAlert = By.className("alert-error");
    private By registerBtn = By.cssSelector("button[title = 'Continue']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setFirstnameInput(String firstname) {
        driver.findElement(firstnameInput).sendKeys(firstname);
    }
    public void setLastnameInput(String lastname) {
        driver.findElement(lastnameInput).sendKeys(lastname);
    }
    public void setEmailInput(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }
    public void setAddress1Input(String address) {
        driver.findElement(address1Input).sendKeys(address);
    }
    public void setCityInput(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }
    public void setPostcodeInput(String postcode) {
        driver.findElement(postcodeInput).sendKeys(postcode);
    }
    public void setLoginNameInput(String loginName) {
        driver.findElement(loginNameInput).sendKeys(loginName);
    }
    public void setPasswordInput(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void setConfirmPasswordInput(String confirmPassword) {
        driver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
    }
    private Select findRegionInput(){
        return new Select(driver.findElement(regionInput));
    }
    public void setRegionInput(String region){
        Waiter.waitFor(driver).until(ExpectedConditions.textToBePresentInElement(driver.findElement(regionInput), region));
        findRegionInput().selectByVisibleText(region);
    }
    private Select findCountryInput(){
        return new Select(driver.findElement(countryInput));
    }
    public void setCountryInput(String country) {
        findCountryInput().selectByVisibleText(country);
    }
    public void clickToAgreePrivacyPolicy(){
        driver.findElement(privacyPolicyBtn).click();
    }
    public String getErrorAlert(){
        return driver.findElement(errorAlert).getText();
    }
    public AccountCreatedPage clickRegisterBtn(){
        driver.findElement(registerBtn).click();
        return new AccountCreatedPage(driver);
    }

    public void storeRegisteredData(String loginName, String password) throws IOException {
        String excelPath = "resources/data-files/registeringData.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook workBook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workBook.getSheetAt(0);

        int lastRow = sheet.getLastRowNum();
        int newRow = lastRow + 1;

        Row row = sheet.createRow(newRow);

        Cell cell1 = row.createCell(0);
        Cell cell2 = row.createCell(1);
        cell1.setCellValue(loginName);
        cell2.setCellValue(password);

        FileOutputStream fos = new FileOutputStream(excelPath);
        workBook.write(fos);

        fos.close();
        fis.close();
    }
}
