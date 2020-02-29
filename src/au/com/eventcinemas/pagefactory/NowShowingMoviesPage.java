package au.com.eventcinemas.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author ujjwal keshri
 * @date 29/02/2020
 */
public class NowShowingMoviesPage {

    private WebDriver driver;



    public NowShowingMoviesPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean verifyNowShowingPage(){
        String expected = "Now Showing - Event Cinemas";
        return expected.equals(driver.getTitle());
    }

    public MovieDescriptionPage selectMovie(String movieName){
        String css = "div[data-name='"+movieName+"']";
        driver.findElement(By.cssSelector(css)).click();
        return new MovieDescriptionPage(driver);
    }
}
