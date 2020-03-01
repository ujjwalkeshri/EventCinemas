package au.com.eventcinemas.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author ujjwal keshri
 * @date 1/03/2020
 */
public class MovieDescriptionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "div#add-cinemas")
    WebElement btnAddCinema;

    @FindBy(css = "div#add-cinemas span[class='state'][data-state='NSW']")
    WebElement btnNSWLocation;

    @FindBy(css = "div#add-cinemas a[data-name='Parramatta'][id*='cinema-select']")
    WebElement checkBoxParramattaCinema;

    @FindBy(css = "div#add-cinemas a[class*='blue done btn']")
    WebElement btnDone;

    @FindBy(css = "a[class='session-btn']")
    List<WebElement> sessions;

    public MovieDescriptionPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public boolean verifyMovieTitle(String movieName){
        String expected = movieName+" - Event Cinemas";
        return expected.equals(driver.getTitle());
    }

    public void clickAddCinema(){
        btnAddCinema.click();
    }

    public void selectNSWLocation(){
        wait.until(ExpectedConditions.visibilityOf(btnNSWLocation));
        btnNSWLocation.click();
    }

    public void selectParramattaLocation(){
        wait.until(ExpectedConditions.visibilityOf(checkBoxParramattaCinema));
        checkBoxParramattaCinema.click();
    }

    public void clickDone(){
        btnDone.click();
    }

    public void selectNSWParramattaCinema(){
        clickAddCinema();
        selectNSWLocation();
        selectParramattaLocation();
        clickDone();
    }

    public void selectMovieDate(String date){
        String css = "a[data-date='"+date+"']";
        driver.findElement(By.cssSelector(css)).click();
    }

    public boolean verifySeatsAvailable(){
        int numberOfSeat = Integer.parseInt(sessions.get(0).getAttribute("data-seatsavailable"));
        return numberOfSeat>0;
    }

    public SeatSelectionPage selectSession(){
        sessions.get(0).click();
        return new SeatSelectionPage(driver);
    }


}
