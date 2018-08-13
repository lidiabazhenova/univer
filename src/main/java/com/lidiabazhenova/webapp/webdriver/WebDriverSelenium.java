package com.lidiabazhenova.webapp.webdriver;

import com.lidiabazhenova.webapp.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverSelenium {

    private static final String SCRIPT_CLICK = "arguments[0].click()";


    public static StringBuilder runSeleniumWebdriver(final List<Product> products) throws Exception {
        final StringBuilder description = new StringBuilder();

        WebDriver driver = WebDriverFactory.getInstance();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
            WebDriverWait wait = (new WebDriverWait(driver, 10));
            for (final Product product : products) {
                driver.get(product.getProductUrl());

                final BigDecimal priceInDateBase = new BigDecimal(product.getProductPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
                final By priceOnPage = By.cssSelector(".b-product-control__text.b-product-control__text_main");
                wait.until(ExpectedConditions.visibilityOfElementLocated(priceOnPage));
                String priceFromPage = StringUtils.removeEnd(driver.findElement(priceOnPage).getText(), "руб.");
                priceFromPage = priceFromPage.replace(",", ".");

                final BigDecimal price = new BigDecimal(Double.valueOf(priceFromPage)).setScale(2, BigDecimal.ROUND_HALF_UP);

                if (priceInDateBase.equals(price)) {
                    WebElement buttonPutInBasket =
                            driver.findElement(By.cssSelector("div.b-product__content span.i-button__text"));
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

    private static boolean goToBasketAndApplyQuantities(final List<Product> products, final WebDriver driver,
                                                        final StringBuilder description) {
        try {
            WebDriverWait wait = (new WebDriverWait(driver, 4));

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div//span[@class='top-panel__userbar__cart__count']")));
            WebElement buttonGoToBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]"));
            clickElement(buttonGoToBasket, driver);

            // TODO : apply quantities by list

            final String spanAmountXpath = "//li[@class='i-amount-select__item' and @data-value='%s']/span";
            final String inputAmountXpath
                    = "//a[contains(@href,'%s')]//ancestor::tr//input[contains(@class, 'i-amount-select__key')]";

            for (final Product product : products) {
                final int productQuantity = product.getProductQuantity();
                WebElement inputProductAmount = driver.findElement(By
                        .xpath(String.format(inputAmountXpath, product.getProductUrl())));
                inputProductAmount.click();
                if (productQuantity != 0 && productQuantity <= 10) {
                    final WebElement span = driver.findElement(By.xpath(String.format(spanAmountXpath, String.valueOf(productQuantity))));
                    clickElement(span, driver);
                    try {
                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", Integer.toString(productQuantity)));
                    } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                        inputProductAmount = driver.findElement(By
                                .xpath(String.format(inputAmountXpath, product.getProductUrl())));
                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", Integer.toString(productQuantity)));
                    }
                } else {
                    inputProductAmount = driver.findElement(By
                            .xpath(String.format(inputAmountXpath, product.getProductUrl())));
                    clickElement(inputProductAmount, driver);
                    final WebElement spanNoReadOnly = driver.findElement(By.xpath(String.format(spanAmountXpath, "10")));
//                    clickElement(spanNoReadOnly, driver);
//                    wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", "10"));
//                    inputProductAmount.sendKeys("10");
//                    try {
//                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", "10"));
//                    } catch (org.openqa.selenium.StaleElementReferenceException ex) {
//                        inputProductAmount = driver.findElement(By
//                                .xpath(String.format(inputAmountXpath, product.getProductUrl())));
//                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", "10"));
//
//                    }

                    Actions actions = new Actions(driver);
                    actions.click(spanNoReadOnly)
                            .sendKeys(String.valueOf(productQuantity))
                            .build().perform();

//                    try {
//                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", "10"));
//                        inputProductAmount = driver.findElement(By
//                                .xpath(String.format(inputAmountXpath, product.getProductUrl())));
//                        inputProductAmount.sendKeys(Integer.toString(productQuantity));
//                    } catch (org.openqa.selenium.StaleElementReferenceException ex) {
//                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", "10"));
//                        inputProductAmount = driver.findElement(By
//                                .xpath(String.format(inputAmountXpath, product.getProductUrl())));
//                        inputProductAmount.sendKeys(Integer.toString(productQuantity));
//                    }
                    wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", Integer.toString(productQuantity)));
                }
            }

            description.append("\r\nОбновление количества в корзине прошло успешно\r\n");

        } catch (
                final Exception ex)

        {
            ex.printStackTrace();
            description.append("Ошибка при обновлении количества в корзине\r\n");
            return false;
        }

        //check amount if ()
        return true;
    }

    private static boolean doCheckout(final WebDriver driver, final StringBuilder description) {
        try {
            WebDriverWait wait = (new WebDriverWait(driver, 6));
            //TODO:delete wait
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
