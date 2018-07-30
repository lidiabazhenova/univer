package com.lidiabazhenova.webapp.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public final class WebDriverFactory {

    private static WebDriver driver;

    public static WebDriver getInstance() throws Exception {
        Properties properties = new Properties();
        properties.load(WebDriverSelenium.class.getClassLoader().getResourceAsStream("data.properties"));
        String browserName = properties.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.out.println(browserName);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            options.addArguments("start-maximized");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);

        } else if (browserName.equals("firefox")) {
            System.out.println(browserName);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        return driver;
    }
}
