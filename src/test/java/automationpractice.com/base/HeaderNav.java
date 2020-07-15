package automationpractice.com.base;

import org.openqa.selenium.By;

public class HeaderNav extends TestBase{
    // class, which describes HeaderNav elements
    public void clickOnNavItem(String navItem) {
        log.info("Method is executed");
        String navItemXpath =  "//div[@class='header_user_info']//a[normalize-space()=" + "'" + navItem + "'"+ "]";
        log.info(navItemXpath);
        driver.findElement(By.
                xpath(navItemXpath))
                .click();
    }
}
