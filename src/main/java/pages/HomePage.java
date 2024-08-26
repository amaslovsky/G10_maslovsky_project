package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.outlet.Outlet;
import pages.spalnya.Spalnya;
import variables.Variables;

import java.time.Duration;
import java.util.ArrayList;

public class HomePage extends ActionsOnElements {
    public ArrayList<String> namesOfSelectedProducts = new ArrayList<>();
    public ArrayList<String> getNamesOfSelectedProducts() {
        return namesOfSelectedProducts;
    }

    protected String pageUrl = Variables.url;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage openHomePage() {
        webDriver.get(Variables.url);
        closeCookiePopup();
        Assert.assertTrue("HomePage page is not loaded",
                webDriver.findElement(By.xpath("//div[@class='frontpage']")).isDisplayed());
        logger.info("HomePage was opened " + Variables.url);
        return this;
    }

    private HomePage closeCookiePopup() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement cookiePopupCloseButton = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//button[contains(@onclick, 'declineAll')]")));
        clickOnElement(cookiePopupCloseButton);
        logger.info("Cookie popup was closed");
        webDriver.switchTo().defaultContent();
        return this;
    }

    public Spalnya openSpalnyaPage() {
        WebElement spl = webDriver.findElement(By.xpath("//div[contains(@class, 'category')]//span[text()='Спальня']"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(spl).perform();
        clickOnElement(spl);
        return new Spalnya(webDriver);
    }

    public Outlet openOutletPage() {
        clickOnElement("//span[text()='Outlet']");
        return new Outlet(webDriver);
    }

    public MenuElement getMenuElement() {
        return new MenuElement(webDriver);
    }

    public HomePage clickOnMenuButton() {
        clickOnElement("//span[text()='Меню']");
        return this;
    }
}
