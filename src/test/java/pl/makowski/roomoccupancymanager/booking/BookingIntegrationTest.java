package pl.makowski.roomoccupancymanager.booking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BookingIntegrationTest extends BasicIntegrationTest {

    @Test
    public void shouldCalculateIncome() throws Exception {
        //given
        addOffer(23);
        addOffer(45);
        addOffer(155);
        addOffer(374);
        addOffer(22);
        addOffer(99.99);
        addOffer(100);
        addOffer(101);
        addOffer(115);
        addOffer(209);
        //then
        getBookingCalculation(3, 3)
                .andExpect(jsonPath("$.economy", is(167.99)))
                .andExpect(jsonPath("$.premium", is(738.0)));
    }

}
