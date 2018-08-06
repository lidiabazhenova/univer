package com.lidiabazhenova.webapp.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Example {
    private static final String SCRIPT_CLICK = "arguments[0].click()";


    public static void main(String[] args) {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get("https://oz.by/copybooks/more10569558.html");

        WebElement buttonPutInBasket = driver.findElement(By.cssSelector("div.b-product__content span.i-button__text"));
        clickElement(buttonPutInBasket, driver);
        WebElement buttonGoToBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]"));
        clickElement(buttonGoToBasket, driver);

        String spanAmountXpath = "//li[@class='i-amount-select__item' and @data-value='%s']/span";
        final int productQuantity = 15;
        WebElement inputQuantity;

        List<WebElement> list = driver.findElements(By.cssSelector(".i-amount-select__key"));

        if (productQuantity != 0 && productQuantity <= 9) {
            inputQuantity = driver.findElement(By.cssSelector(".i-amount-select__key"));
            inputQuantity.click();
            driver.findElement(By.xpath(String.format(spanAmountXpath, String.valueOf(productQuantity)))).click();
        } else {
            try {
                driver.findElement(By.cssSelector(".i-amount-select__key")).click();
                Actions actions = new Actions(driver);
                actions.click(driver.findElement(By.xpath(String.format(spanAmountXpath, "10"))))
                        .sendKeys("22")
                        .build().perform();

                JavascriptExecutor js = (JavascriptExecutor) driver;
                inputQuantity = driver.findElement(By.cssSelector(".i-amount-select__key"));
                js.executeScript("arguments[0].value='22';", inputQuantity);
                //js.executeScript("focus();", inputQuantity);

            } catch (final Exception ex) {
                ex.printStackTrace();
            }
        }
        //driver.quit();
    }

    private static void clickElement(final WebElement element, WebDriver driver) {
        try {
            element.click();
        } catch (final WebDriverException ex) {
            JavascriptExecutor je = (JavascriptExecutor) driver;
            je.executeScript(SCRIPT_CLICK, element);
        }
    }
}
