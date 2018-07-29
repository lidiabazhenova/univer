package com.lidiabazhenova.webapp.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverSelenium {
    static WebDriverWait wait;

    public void runSeleniumRobot() throws Exception {
        WebDriver driver = WebDriverFactory.getInstance();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get("https://oz.by");

        driver.findElement(By.xpath("//a[@class=\"top-panel__userbar__auth\"]")).click();
        driver.findElement(By.xpath("//a[@id=\"loginFormLoginEmailLink\"]")).click();
        driver.findElement(By.xpath(".//*[@id='loginForm']//input[@name=\"cl_email\"]")).sendKeys("testselenium@tut.by");
        driver.findElement(By.xpath("//input[@name=\"cl_psw\"]")).sendKeys("testselenium");
        driver.findElement(By.xpath("//*[@id='loginForm']/button[@value=\"login\"]")).click();

        wait = (new WebDriverWait(driver, 6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='top-panel__userbar__user__name__inner']")));
        driver.get("https://oz.by/stationery/more10571950.html?sbtoken=88b118047983de3f728d85deb9507f4b");

        WebElement buttonPutInBasket = driver.findElement(By.cssSelector("div.b-product__content span.i-button__text"));
        if (buttonPutInBasket.getText().equals("Уже в корзине")) {
            System.out.println("Уже в корзине");
        } else if (buttonPutInBasket.getText().equals("Положить в корзину")) {
            buttonPutInBasket.click();
        }

        WebElement buttonGoToBasket = driver.findElement(By.xpath(".//a/u[contains(text(), \"Корзина\")]"));

        wait = (new

                WebDriverWait(driver, 6));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//span[@class='top-panel__userbar__cart__count']")));
        buttonGoToBasket.click();

        wait = (new

                WebDriverWait(driver, 6));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='checkout-block-submit']")));
        driver.findElement(By.xpath(".//*[@id='checkout-block-submit']")).

                click();

        driver.findElement(By.xpath("//*[@id='enter-phone']//input")).

                sendKeys("333333333");
    }
    private void tearDown() throws Exception {
        if (WebDriverFactory.getInstance() != null) {
            WebDriverFactory.getInstance().quit();
        }
    }
}
