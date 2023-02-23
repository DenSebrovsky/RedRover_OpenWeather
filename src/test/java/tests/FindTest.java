package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.components.TopMenu;

public class FindTest extends BaseTest {

    @Test
    public void testNavigationToFindPage_WithTheSameCityName() {
        final String cityName = "Rome";
        final String attributeName = "value";

        String enteredCityName = openBaseURL()
                .getTopMenu()
                .inputSearchCriteriaIntoSearchField(cityName)
                .getTopMenu()
                .getEnteredValue();

        Assert.assertEquals(enteredCityName, cityName);

        String findPageSearchFieldValue = new TopMenu(getDriver())
                .clickEnter()
                .getSearchFieldValue(attributeName);

        Assert.assertEquals(findPageSearchFieldValue, enteredCityName);
        Assert.assertEquals(findPageSearchFieldValue, cityName);
    }

//    @Test
//    public void testCityInSearchFieldContainsCity() {
//        final String expectedCityName = "Rome";
//
//        List<WebElement> actualResultList = openBaseURL()
//                .getTopMenu()
//                .inputSearchCriteriaIntoSearchField(expectedCityName)
//                .clickEnter()
//                .getResultRows();
//
//        Assert.assertTrue(actualResultList.size() > 0);
//
//        List<String> cityNames = new FindPage(getDriver()).getCityCountryNames();
//
//        for (String cityName : cityNames) {
//            Assert.assertEquals(TestUtils.getSubstring(cityName, ","), expectedCityName);
//        }
//    }
}
