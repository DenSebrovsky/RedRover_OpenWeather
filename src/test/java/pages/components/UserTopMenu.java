package pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base_abstract.BaseComponents;

import java.util.ArrayList;
import java.util.List;

public class UserTopMenu extends BaseComponents {

    @FindBy(xpath = "//ul[@id='myTab']/li")
    private List<WebElement> navTabLinks;

    public UserTopMenu(WebDriver driver) {
        super(driver);
    }

    public int getUserTopMenusAmount() {

        return getListSize(navTabLinks);
    }

    public List<String> clickUserTopMenus() {
        List<String> urlList = new ArrayList<>();
        urlList.add(getCurrentURL());

        for (int i = 1; i < navTabLinks.size(); i++) {
            click(navTabLinks.get(i));
            urlList.add(getCurrentURL());

            if (i == 6) {
                goBack();
            }
        }

        return urlList;
    }
}
