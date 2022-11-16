import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.List;

public class NataliaRamanenkaTest extends BaseTest {
    final static String BASE_URL = "https://openweathermap.org/";
    final static By SEARCH_GUIDE_BUTTON = By.xpath(
            "//div[@id = 'desktop-menu']/ul/li/a[@href = '/guide']");
    final static By SEARCH_HEAD_OF_PAGE_GUIDE = By.xpath(
            "//div[@class='col-sm-7']/h1");

    private void openBaseURL() {
        getDriver().get(BASE_URL);
    }

    private void waitForGrayFrameDisappeared() {
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("owm-loader-container")));
    }
    private void click(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private void waitElementToBeVisible(By by, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private String actualUrl() {

        return getDriver().getCurrentUrl();
    }

    private String actualTitle() {

        return getDriver().getTitle();
    }

    @Test
    public void testGuideLinkAndTitle() throws InterruptedException {
        String expectedResultUrl = "https://openweathermap.org/guide";
        String expectedResultTitle = "OpenWeatherMap API guide - OpenWeatherMap";

        openBaseURL();
        waitForGrayFrameDisappeared();

        click(SEARCH_GUIDE_BUTTON, getWait5());
        waitElementToBeVisible(SEARCH_HEAD_OF_PAGE_GUIDE, getWait5());

        Assert.assertEquals(actualUrl(), expectedResultUrl);
        Assert.assertEquals(actualTitle(), expectedResultTitle);
    }
    @Ignore
    @Test
    public void testUnitsOfMeasurementIsFaringates() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String measure = "F";
        boolean expectedResult = true;

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchImperialF = getDriver().findElement(By.xpath("//div[text() = 'Imperial: °F, mph']"));
        searchImperialF.click();
        Thread.sleep(2000);
        WebElement searchF = getDriver().findElement(By.xpath("//span[@class = 'heading']"));
        boolean actualResult = searchF.getText().contains(measure);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testCookiesPanelWithButtonsIs() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. We also use non-essential cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCookiesPanel = getDriver().findElement(
                By.xpath("//p[@class ='stick-footer-panel__description']"));
        String actualResult1 = searchCookiesPanel.getText();
        WebElement searchButtonAllowAll = getDriver().findElement(
                By.xpath("//button[@class ='stick-footer-panel__link']"));
        String actualResult2 = searchButtonAllowAll.getText();
        WebElement searchButtonManageCookies = getDriver().findElement(
                By.xpath("//a[@href ='/cookies-settings']"));
        String actualResult3 = searchButtonManageCookies.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }

    @Ignore
    @Test
    public void testSupportWithSubmenuIs() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchSupport = getDriver().findElement(By.xpath("//div[@id= 'support-dropdown']"));
        searchSupport.click();
        Thread.sleep(3000);
        WebElement searchSupportFAQ = getDriver().findElement(
                By.xpath("//ul[@id= 'support-dropdown-menu'] /li/a[@href = '/faq']"));
        String actualResult1 = searchSupportFAQ.getText();
        WebElement searchSupportHowToStart = getDriver().findElement(
                By.xpath("//ul[@id= 'support-dropdown-menu']/li/a[@href = '/appid']"));
        String actualResult2 = searchSupportHowToStart.getText();
        WebElement searchSupportAskAQuestion = getDriver().findElement(
                By.xpath("//ul[@id= 'support-dropdown-menu']/li/a[@href = 'https://home.openweathermap.org/questions']")
        );
        String actualResult3 = searchSupportAskAQuestion.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
    }
    @Ignore
    @Test
    public void testUnitsOfMeasurementIsCelsius() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String measure1 = "F";
        String measure2 = "C";
        boolean expectedResult1 = true;
        boolean expectedResult2 = true;

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchImperialF = getDriver().findElement(By.xpath("//div[text() = 'Imperial: °F, mph']"));
        searchImperialF.click();
        Thread.sleep(3000);
        WebElement searchF = getDriver().findElement(By.xpath("//span[@class = 'heading']"));
        boolean actualResult1 = searchF.getText().contains(measure1);
        WebElement searchImperialC = getDriver().findElement(By.xpath("//div[text() = 'Metric: °C, m/s']"));
        searchImperialC.click();
        Thread.sleep(3000);
        WebElement searchC = getDriver().findElement(By.xpath("//span[@class = 'heading']"));
        boolean actualResult2 = searchF.getText().contains(measure2);

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Ignore
    @Test
    public void testLink_WhenLogoClick() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String expectedResult = url;

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchLogo = getDriver().findElement(By.xpath("//li[@class = 'logo']"));
        searchLogo.click();
        Thread.sleep(7000);
        String actualResult = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Ignore
    @Test
    public void testLinkHasFindAndCity() throws InterruptedException {
        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String action = "find";
        boolean expectedResult1 = true;
        String expectedResult2 = cityName;

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchCityWeather = getDriver().findElement(
                By.xpath("//div//input[@placeholder = 'Weather in your city']"));
        searchCityWeather.click();
        searchCityWeather.sendKeys(cityName);
        searchCityWeather.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        if (getDriver().getCurrentUrl().contains(cityName) && getDriver().getCurrentUrl().contains(action)){
            boolean actualResult1 = true;
            Assert.assertEquals(actualResult1, expectedResult1);
        } else {
            boolean actualResult1 = false;
            Assert.assertEquals(actualResult1, expectedResult1);
        }
        WebElement searchRome = getDriver().findElement(By.xpath("//input[@id = 'search_str']"));
        String actualResult2 = searchRome.getAttribute("value");

        Assert.assertEquals(actualResult2, expectedResult2);
    }

    @Ignore
    @Test
    public void testFindOrangeButtons() throws InterruptedException {
        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        getDriver().get(url);
        Thread.sleep(10000);
        WebElement searchAPI = getDriver().findElement(
                By.xpath("//div[@id ='desktop-menu']//a[@href = '/api']"));
        searchAPI.click();
        Thread.sleep(5000);
        List<WebElement> searchBtnOrange = getDriver().findElements(
                By.xpath("//a[contains(@class, 'orange')]"));
        int actualResult = searchBtnOrange.size();

        Assert.assertEquals(actualResult, expectedResult);
    }

}
