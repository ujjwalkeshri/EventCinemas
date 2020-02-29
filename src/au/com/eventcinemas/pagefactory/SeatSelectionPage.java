package au.com.eventcinemas.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author ujjwal keshri
 * @date 1/03/2020
 */
public class SeatSelectionPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "li[class='couple-seat double']")
    List<WebElement> btnCoupleSeat;

    @FindBy(css = "a[class='btn black proceed seats']")
    WebElement btnProceed;

    public SeatSelectionPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void selectFirstAvailableCoupleSeat(){
        btnCoupleSeat.get(0).click();
    }

    public TicketsPage clickProceedButton(){
        btnProceed.click();
        return new TicketsPage(driver);
    }

    public TicketsPage selectAvailableSeatAndProceed(){
        selectFirstAvailableCoupleSeat();
        return clickProceedButton();
    }


}
