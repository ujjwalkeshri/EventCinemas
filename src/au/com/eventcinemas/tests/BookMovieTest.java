package au.com.eventcinemas.tests;

import au.com.eventcinemas.base.BaseSetup;
import au.com.eventcinemas.pagefactory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author ujjwal keshri
 * @date 1/03/2020
 */
public class BookMovieTest extends BaseSetup {

    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = getDriver();
    }

    @Test
    public void bookMovieTickets(){

        String movieName = "Fantasy Island";
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.verifyHomePageTitle()," Home page title doesn't match");

        //Go to now showing movies page
        NowShowingMoviesPage nowShowingMoviesPage = homePage.navigateToMoviesPage();
        Assert.assertTrue(nowShowingMoviesPage.verifyNowShowingPage(),
                "Now Showing movie page title doesn't match");

        //select desired movie
        MovieDescriptionPage descriptionPage = nowShowingMoviesPage.selectMovie(movieName);
        Assert.assertTrue(descriptionPage.verifyMovieTitle(movieName), "Movie name doesn't match");

        descriptionPage.selectNSWParramattaCinema();

        //select movie date and select the session if seat available
        descriptionPage.selectMovieDate("2020-03-03");
        Assert.assertTrue(descriptionPage.verifySeatsAvailable()," Seat is not available ");
        SeatSelectionPage seatSelectionPage = descriptionPage.selectSession();

        //select couple seat if available
        TicketsPage ticketsPage = seatSelectionPage.selectAvailableSeatAndProceed();

        //select two Adult tickets
        String indPrice = ticketsPage.selectNumberOfAdultTickets("2");
        Assert.assertEquals(indPrice, "21");

        //Assert total price of the booking including booking fee 21*2+1.50*2=45
        Assert.assertTrue(ticketsPage.validateTotalBookingPrice(),"Total Booking price does not match");


    }
}
