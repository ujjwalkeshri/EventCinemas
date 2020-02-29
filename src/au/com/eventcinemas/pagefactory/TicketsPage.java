package au.com.eventcinemas.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * @author ujjwal keshri
 * @date 1/03/2020
 */
public class TicketsPage {

    private WebDriver driver;

    @FindBy(css = "select.qty")
    List<WebElement> ticketTypeAndQuantity;

    public TicketsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String selectNumberOfAdultTickets(String qty) {
        String individualTicketPrice="";
        for (WebElement ticketType : ticketTypeAndQuantity) {
            if (ticketType.getAttribute("data-ticket").contains("\"Name\":\"Adult\"")) {
                String details = ticketType.getAttribute("data-ticket");
                String[] temp = details.split(",");
                for(String t: temp){
                    if(t.contains("Price")){
                        individualTicketPrice = t.split(":")[1];
                    }
                }
                new Select(ticketType).selectByValue(qty);
            }
        }
        return individualTicketPrice;
    }

}
