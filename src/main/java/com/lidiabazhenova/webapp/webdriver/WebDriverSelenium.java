package com.lidiabazhenova.webapp.webdriver;

import com.lidiabazhenova.webapp.model.Product;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebDriverSelenium {

    private static final String SCRIPT_CLICK = "arguments[0].click()";
    private static final By indicatorLoading = By.cssSelector(".deal-form_footer-processing");


    public static StringBuilder runSeleniumWebdriver(final List<Product> products) throws Exception {
        final StringBuilder description = new StringBuilder();

        WebDriver driver = WebDriverFactory.getInstance();
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            driver.get("https://oz.by");

            if (!doLogin(driver, description)) {
                driver.quit();
                return description;
            }

            if (!cleanBasket(driver, description)) {
                driver.quit();
                return description;
            }

            if (!orderProducts(products, driver, description)) {
                driver.quit();
                return description;
            }

            if (!goToBasketAndApplyQuantities(products, driver, description)) {
                driver.quit();
                return description;
            }

            if (!doCheckout(driver, description)) {
                driver.quit();
                return description;
            }

        } finally {
            driver.quit();
        }

        return description;
    }

    private static boolean doLogin(final WebDriver driver, final StringBuilder description) {

        try {
            final WebElement openUserAuthPanelButton = driver.findElement(By.xpath("//a[@class=\"top-panel__userbar__auth\"]"));
            clickElement(openUserAuthPanelButton, driver);

            final WebElement loginFormButton = driver.findElement(By.xpath("//a[@id=\"loginFormLoginEmailLink\"]"));
            clickElement(loginFormButton, driver);

            final WebElement emailInput = driver.findElement(By.xpath(".//*[@id='loginForm']//input[@name=\"cl_email\"]"));
            emailInput.sendKeys("testselenium@tut.by");

            final WebElement passwordInput = driver.findElement(By.xpath("//input[@name=\"cl_psw\"]"));
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
            WebDriverWait wait = (new WebDriverWait(driver, 4));

            final By userbarLocator = By.xpath("//*[@class='top-panel__userbar__user__name__inner']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(userbarLocator));

            WebElement countItemsInBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]/span"));
            WebElement buttonGoToBasket = driver.findElement(By.xpath("//a[u[contains(text(), 'Корзина')]]"));

            if (countItemsInBasket.isDisplayed()) {
                clickElement(buttonGoToBasket, driver);

                final WebElement selectAllCheckbox = driver.findElement(
                        By.cssSelector(".goods-table__row.goods-table__row_footer .i-checkbox__faux"));
                clickElement(selectAllCheckbox, driver);

                wait.until(ExpectedConditions.invisibilityOfElementLocated(indicatorLoading));
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
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(indicatorLoading));
                } else {
                    inputProductAmount = driver.findElement(By
                            .xpath(String.format(inputAmountXpath, product.getProductUrl())));
                    clickElement(inputProductAmount, driver);
                    final WebElement spanNoReadOnly = driver.findElement(By.xpath(String.format(spanAmountXpath, "10")));

                    Actions actions = new Actions(driver);
                    actions.click(spanNoReadOnly)
                            .sendKeys(String.valueOf(productQuantity))
                            .build().perform();
                    try {
                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", Integer.toString(productQuantity)));
                    } catch (org.openqa.selenium.StaleElementReferenceException ex) {
                        inputProductAmount = driver.findElement(By
                                .xpath(String.format(inputAmountXpath, product.getProductUrl())));
                        wait.until(ExpectedConditions.attributeContains(inputProductAmount, "value", Integer.toString(productQuantity)));

                    }
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

            WebElement selectDeliveryLink = driver.findElement(By.xpath("//*[@id='select-delivery-link']//a"));
            clickElement(selectDeliveryLink, driver);

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".deal-form-main__input_active")));

            Select selectCityDropdown = new Select(driver.findElement(By.name("current-city")));
            selectCityDropdown.selectByVisibleText("Витебск, Витебская область");

            final WebElement deliveryOption = driver.findElement(
                    By.xpath("//div[@class='i-context-box-list__line' and contains(text(), 'Самовывоз в Витебске')]"));
            clickElement(deliveryOption, driver);

            final WebElement deliveryApply = driver.findElement(By.name("delivery-apply"));
            clickElement(deliveryApply, driver);

            WebElement selectDeliverySpan = driver.findElement(By.xpath("(//*[@id='select-delivery-link']//span)[1]"));
            try {
                wait.until(ExpectedConditions.attributeContains(selectDeliverySpan, "value", "Самовывоз в Витебске"));
            } catch (org.openqa.selenium.StaleElementReferenceException ex) {

                selectDeliverySpan = driver.findElement(By.xpath("(//*[@id='select-delivery-link']//span)[1]"));
                wait.until(ExpectedConditions.textToBePresentInElement(selectDeliverySpan, "Самовывоз в Витебске"));
            }

            WebElement selectPaymentLink = driver.findElement(By.id("select-payment-link"));
            clickElement(selectPaymentLink, driver);

            final WebElement paymentByCash = driver.findElement(By.xpath("(//div[@class='i-context-box-list__line'])[1]"));
            clickElement(paymentByCash, driver);

            final WebElement paymentApply = driver.findElement(By.name("payment-apply"));
            clickElement(paymentApply, driver);

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
