package tests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import utils.DateTimeUtils;
import java.util.List;

public class MainTest extends BaseTest {

    @Test
    public void testH2TagText_WhenSearchingCityCountry() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL();

        MainPage mainPage = new MainPage(getDriver());

        final String oldCityCountryName = mainPage.getCityCountryName();

        mainPage.clickSearchCityField();
        mainPage.inputSearchCriteria(cityName);
        mainPage.clickSearchButton();
        mainPage.clickParisInDropDownList();
        mainPage.waitForCityCountryNameChanged(oldCityCountryName);

        String newCityCountryNames = mainPage.getCityCountryName();

        Assert.assertEquals(newCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testH2TagText_WhenSearchingCityCountry1() {
        final String cityName = "Paris";
        final String expectedCityCountryNames = "Paris, FR";

        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());

        final String oldCityCountryName = mainPage.getCityCountryName();

        String newCityCountryNames = mainPage
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .clickParisInDropDownList()
                .waitForCityCountryNameChanged(oldCityCountryName)
                .getCityCountryName();

        Assert.assertEquals(newCityCountryNames, expectedCityCountryNames);
    }

    @Test
    public void testH1HeaderColorAndFontSize() {
        final String expectedColor = "rgba(0, 0, 0, 0)";
        final String expectedFontSize = "45px";

        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());

        String h1HeaderColor = mainPage.getColor();
        String h1HeaderFontSize = mainPage.getFontSize();

        Assert.assertEquals(h1HeaderColor, expectedColor);
        Assert.assertEquals(h1HeaderFontSize, expectedFontSize);
    }

    @Test
    public void testAmountOfIcons_OnDifferentWeatherPopUp() {
        final int expectedAmountOfIcons = 9;

        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());

        int elements = mainPage
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .getAmountOfIconsOnDifferentWeatherPopUp();

        Assert.assertEquals(elements, expectedAmountOfIcons);
    }

    @Test
    public void testEachIconBecomesActiveAfterClick_DifferentWeatherPopUp_() {
        final String nameOfTestedAttribute = "class";
        final String expectedValueOfTestedAttribute = "activeIcon";

        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());

        List<WebElement> elements = mainPage
                .clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .getListOfIconsOnDifferentWeatherPopUp();

        for (WebElement element : elements) {
            Assert.assertEquals(
                    mainPage
                            .clickIconOnDifferentWeatherPopUp(element)
                            .getAttribute(element, nameOfTestedAttribute),
                    expectedValueOfTestedAttribute
            );
        }
    }

    @Test
    public void testRefreshPage() {
        final String expectedTextWhenLoading = "Loading";

        String actualTextWhenLoading = openBaseURL_ReturnMainPage()
                .clickLogo()
                .getLoadingText("aria-label");

        Assert.assertEquals(actualTextWhenLoading, expectedTextWhenLoading);
    }

    @Test
    public void testCurrentLocation() {
        final String cityName = "Norway";
        final String expectedCityName = "London, GB";

        String actualCityName = openBaseURL_ReturnMainPage()
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .clickCityNorway()
                .clickLocationButton()
                .getCityCountryName();

        Assert.assertEquals(actualCityName, expectedCityName);
    }

    @Test
    public void testAllIconsAreShownAndClickable() {
        MainPage mainPage = openBaseURL_ReturnMainPage()
                .clickDifferentWeatherButton();

        Assert.assertTrue(mainPage.checkAllIconsAreVisibleAndClickable());
    }

    @Test
    public void testIfXButtonDisplayed() {
        MainPage mainPage = openBaseURL_ReturnMainPage()
                .clickDifferentWeatherButton();

        Assert.assertTrue(mainPage.isXButtonDisplayed());
    }

    @Test
    public void testTempUnitChangedToC_WhenSwitchingToMetric() {
        MainPage mainPage = openBaseURL_ReturnMainPage().switchToMetric();
        waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isTextContainsC());
    }

    @Test
    public void testTempUnitChangedToF_WhenSwitchingToImperial() {
        MainPage mainPage = openBaseURL_ReturnMainPage().switchToImperial();
        waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isTextContainF());
    }

    @Test
    public void testIfSendButtonDisplayed() {
        MainPage mainPage = openBaseURL_ReturnMainPage()
                .clickDifferentWeatherButton();

        Assert.assertTrue(mainPage.isSendButtonDisplayed());
    }

    @Test
    public void testErrorMessageWhenCityDoesNotExists() {
        final String cityName = "Rrr";
        final String expectedErrorText = "No results for Rrr";

        String actualErrorText = openBaseURL_ReturnMainPage()
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .getErrorText();

        Assert.assertEquals(actualErrorText, expectedErrorText);
    }

    @Test
    public void testIfXButtonHasColorGreen() {
        final String cityName = "Rrr";
        final String expectedXButtonBackgroundColor = "rgba(120, 203, 191, 0.8)";

        String actualXButtonBackgroundColor = openBaseURL_ReturnMainPage()
                .clickSearchCityField()
                .inputSearchCriteria(cityName)
                .clickSearchButton()
                .getErrorButtonBackgroundColor();

        Assert.assertEquals(actualXButtonBackgroundColor, expectedXButtonBackgroundColor);
    }

    @Test
    public void testOnlyOneIconHighlighted_WhenDifferentWeatherContainerAppears() {
        openBaseURL_ReturnMainPage();

        MainPage mainPage = new MainPage(getDriver());

        int activeIcon = mainPage.clickDifferentWeatherButton()
                .waitUntilDifferentWeatherPopUpIsVisible()
                .countActiveIconsInDifferentWeatherContainer();

        final String activeIconColor = mainPage.getActiveIconBackgroundColorInHEX();

        Assert.assertEquals(activeIcon, 1);
        Assert.assertEquals(activeIconColor, "#ececed");
    }
      
    @Test
    public void testCheckTempInC_inDayList_whenSwitchingToMetric() {
        MainPage mainPage = openBaseURL_ReturnMainPage()
                .switchToMetric();
        mainPage.waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isDayListValuesContainsC());
    }

    @Test
    public void testDifferentWeatherButtonOpensDifferentWeatherContainer() {
        String expectedHeader = "Different weather";

        String actualHeader = openBaseURL_ReturnMainPage()
                .clickDifferentWeatherButton()
                .getHeaderForDifferentWeatherContainer();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testCheckTempInF_inDayList_whenSwitchingToImperial() {
        MainPage mainPage = openBaseURL_ReturnMainPage()
                .switchToImperial();
        mainPage.waitForGrayContainerDisappeared();

        Assert.assertTrue(mainPage.isDayListValuesContainsF());
    }

    @Test
    public void testCurrentDateAndTimeOnMainPage() {
        String expectedTime = DateTimeUtils.getSystemDate();

        String actualTime = openBaseURL_ReturnMainPage().getActualTime();

        Assert.assertEquals(actualTime, expectedTime);
    }
}
