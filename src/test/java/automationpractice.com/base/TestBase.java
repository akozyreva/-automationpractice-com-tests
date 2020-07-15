package automationpractice.com.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;

public class TestBase {

    // Webdriver, properties, logs, extendreports
    // DB, excel, mail

    // log - log4j (from mvn repo), .log, log4j.properties

    public static WebDriver driver;
    // for reading properties
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger(TestBase.class.getName());
    private String HEADLESS = System.getProperty("headless");

    @BeforeSuite
    public void setUp() {
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/resources/properties/log4j.properties");
        if(driver == null) {
            // for basic properties
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                log.debug("Config file loaded!!!");
                config.load(fis);

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                log.debug("OR file loaded!!!");
                OR.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (config.getProperty("browser").equals("chrome")) {
                ChromeOptions options = new ChromeOptions();
                if( HEADLESS == null){
                    options.addArguments( "--no-sandbox", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
                }
                else if(HEADLESS.equalsIgnoreCase("true")) {
                    options.addArguments("--headless", "--no-sandbox", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
                }

                driver = new ChromeDriver(options);
                log.debug("Chrome lunched");
            }

        }
        driver.get(config.getProperty("testSiteUrl"));
        log.debug("Naviagted to: " + config.getProperty("testssiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                (Integer.parseInt(
                        config.getProperty("implicit.wait")
                ))
                , TimeUnit.SECONDS);


    }

    public boolean isElementPresent(By by) {
        try{
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterSuite
    public void tearDown() {
        if(driver != null) driver.quit();
        log.debug("Test Execution completed!");
    }
}
