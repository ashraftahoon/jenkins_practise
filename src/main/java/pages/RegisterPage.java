package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;
import utilities.Helper;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage( WebDriver driver) {
        this.driver = driver;
    }

    private static final By female = By.className("female");
    private static final By firstName = By.id("FirstName");
    private static final By lastName = By.id("LastName");
    private static final By Email = By.id("Email");
    private static final By Password = By.name("Password");
    private static final By ConfirmPassword = By.id("ConfirmPassword");
    private static final By registerButton = By.id("register-button");


    public RegisterPage Register(String fname, String lname, String password, String confirmPass) {
        ElementActions.click(driver, female,"gender");
        ElementActions.enterText(driver, firstName, fname,"first name");
        ElementActions.enterText(driver, lastName, lname,"last name");
        ElementActions.enterText(driver, Email, Helper.generateUniqueEmail(),"email");
        ElementActions.enterText(driver, Password, password,"password");
        ElementActions.enterText(driver, ConfirmPassword, confirmPass,"confirm password");
        ElementActions.click(driver,registerButton,"Register Button");
        return this;
    }


}
