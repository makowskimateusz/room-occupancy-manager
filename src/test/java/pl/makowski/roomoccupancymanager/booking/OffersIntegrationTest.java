package pl.makowski.roomoccupancymanager.booking;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class OffersIntegrationTest extends BasicIntegrationTest {

    @Test
    public void shouldAddOffer() throws Exception {
        //given
        addOffer(20.0)
                .andExpect(status().isCreated());
        //then
        getOffer()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].price", is(20.0)));
    }

    @Test
    public void shouldGetAllOffers() throws Exception {
        //given
        addOffer(20.0)
                .andExpect(status().isCreated());
        addOffer(99.9)
                .andExpect(status().isCreated());
        //then
        getOffer()
                .andExpect(jsonPath("$.[0].price", is(20.0)))
                .andExpect(jsonPath("$.[1].price", is(99.9)));

    }

}
