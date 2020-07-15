package automationpractice.com.testcases;



import automationpractice.com.base.MainPage;
import automationpractice.com.base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationForm extends MainPage {

    @Test
    public void userCanRegister() {
        log.info("Start Login Test!");
        clickOnNavItem("Sign in");
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
