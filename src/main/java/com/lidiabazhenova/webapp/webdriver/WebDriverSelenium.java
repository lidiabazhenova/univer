package com.lidiabazhenova.webapp.webdriver;

import com.lidiabazhenova.webapp.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverSelenium {

    private static final String SCRIPT_CLICK = "arguments[0].click()";

    public static StringBuilder runSeleniumWebdriver(final List<Product> products) throws Exception {
        final StringBuilder description = new StringBuilder();

        WebDriver driver = WebDriverFactory.getInstance();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //TODO: add to properties
        driver.get("https://oz.by");

        if (!doLogin(driver, description)) {
            return description;
        }

        if (!cleanBasket(driver, description)) {
            return description;
        }

        if (!orderProducts(products, driver, description)) {
            return description;
        }

        if (!goToBasketAndApplyQuantities(products, driver, description)) {
            return description;
        }

        if (!doCheckout(driver, description)) {
            return description;
        }

        // TODO : uncomment after implementation and tests
        // driver.quit();

        return description;
    }

    private static boolean doLogin(final WebDriver driver, final StringBuilder description) {

        try {
            final WebElement openUserAuthPanelButton = driver.findElement(By.xpath("//a[@class=\"top-panel__userbar__auth\"]"));
            clickElement(openUserAuthPanelButton, driver);

            final WebElement loginFormButton = driver.findElement(By.xpath("//a[@id=\"loginFormLoginEmailLink\"]"));
            clickElement(loginFormButton, driver);

            final WebElement emailInput = driver.findElement(By.xpath(".//*[@id='loginForm']//input[@name=\"cl_email\"]"));
            // TODO : move email to properties
            emailInput.sendKeys("testselenium@tut.by");

            final WebElement passwordInput = driver.findElement(By.xpath("//input[@name=\"cl_psw\"]"));
            // TODO : move password to properties
            passwordInput.sendKeys("testselenium");

            final WebElement loginButton = driver.findElement(By.xpath("//*[@id='loginForm']/button[@value=\"login\"]"));
            clickElement(loginButton, driver);

            description.append("Логин прошел успешно\r\n");

        } catch (final Exception ex) {
            ex.printStackTrace();
            description.append("Ошибка при логине\r\n");

            return false;
        }

        return true;
    }

    private static boolean cleanBasket(final WebDriver driver, final StringBuilder description) {
        try {
            WebDriverWait wait = (new WebDriverWait(driver, 6));

            final By userbarLocator = By.xpath("//*[@class='top-panel__userbar__user__name__inner']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(userbarLocator));

            WebElement countItemsInBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]/span"));
            WebElement buttonGoToBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]"));

            if (countItemsInBasket.isDisplayed()) {
                System.out.println("Очистить корзину");
                clickElement(buttonGoToBasket, driver);

                final WebElement selectAllCheckbox = driver.findElement(
                        By.cssSelector(".goods-table__row.goods-table__row_footer .i-checkbox__faux"));
                clickElement(selectAllCheckbox, driver);

                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.deal-form_footer-processing")));
                WebElement buttonDelete = driver.findElement(By.xpath("//button[contains(text(), 'Удалить')]"));
                clickElement(buttonDelete, driver);

                WebElement buttonDeleteConfirm = driver.findElement(By.cssSelector(".remove-yes"));
                clickElement(buttonDeleteConfirm, driver);
            }
            description.append("Очистка корзины прошла успешно\r\n");

        } catch (final Exception ex) {
            ex.printStackTrace();
            description.append("Ошибка при очистке корзины\r\n");

            return false;
        }

        return true;
    }

    private static boolean orderProducts(final List<Product> products, final WebDriver driver, final StringBuilder description) {
        try {
            for (final Product product : products) {
                driver.get(product.getProductUrl());
                Double priceInDateBase = product.getProductPrice();
                String priceFromPage =
                        StringUtils.removeEnd(driver.findElement(By.cssSelector(
                                "div.b-product_has-gallery .b-product-control__text_main")).getText(), "руб.");
                priceFromPage = priceFromPage.replace(",", ".");

                final Double price = Double.valueOf(priceFromPage);

                if (priceInDateBase.equals(price)) {
                    WebElement buttonPutInBasket = driver.findElement(By.cssSelector("div.b-product__content span.i-button__text"));
                    clickElement(buttonPutInBasket, driver);
                } else {
                    description.append("Цена на продукт ").append(product.getProductName()).append(" изменилась");

                    return false;
                }
            }
            description.append("Добавление в корзину прошло успешно\r\n");

        } catch (final Exception ex) {
            ex.printStackTrace();
            description.append("Ошибка при добавлении в корзину\r\n");

            return false;
        }

        return true;
    }

    private static boolean goToBasketAndApplyQuantities(final List<Product> products, final WebDriver driver, final StringBuilder description) {
        try {
            WebDriverWait wait = (new WebDriverWait(driver, 6));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div//span[@class='top-panel__userbar__cart__count']")));
            WebElement buttonGoToBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]"));
            clickElement(buttonGoToBasket, driver);

            // TODO : apply quantities by list
            // find list of elements with quantities from page
            // iterate through elements and apply quantity from products list

            description.append("\r\nОбновление количества в корзине прошло успешно\r\n");
        } catch (final Exception ex) {
            ex.printStackTrace();
            description.append("Ошибка при обновлении количества в корзине\r\n");
            return false;
        }

        return true;
    }

    private static boolean doCheckout(final WebDriver driver, final StringBuilder description) {
        try {
            WebDriverWait wait = (new WebDriverWait(driver, 6));

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='checkout-block-submit']")));
            final WebElement checkoutButton = driver.findElement(By.xpath(".//*[@id='checkout-block-submit']"));
            clickElement(checkoutButton, driver);

            final WebElement phoneInput = driver.findElement(By.xpath("//*[@id='enter-phone']//input"));
            phoneInput.sendKeys("333333333");

            // TODO : feel rest of the fields: address, email and etc.

            description.append("Чекуат прошел успешно\r\n");

        } catch (final Exception ex) {
            ex.printStackTrace();
            description.append("Ошибка при чекауте\r\n");
            return false;
        }

        return true;
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
