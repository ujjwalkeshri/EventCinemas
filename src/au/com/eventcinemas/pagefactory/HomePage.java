package au.com.eventcinemas.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class HomePage {

    private WebDriver driver;

    @FindBy(css = "ul.brands-list a[href='/Movies/NowShowing']")
    WebElement btnMovies;


    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyHomePageTitle(){
        String expected = "Event Cinemas";
        return expected.equals(driver.getTitle());
    }

    public NowShowingMoviesPage navigateToMoviesPage(){
        btnMovies.click();
        return new NowShowingMoviesPage(driver);
    }
}
