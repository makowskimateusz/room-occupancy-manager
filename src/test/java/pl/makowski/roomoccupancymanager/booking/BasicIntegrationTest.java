package pl.makowski.roomoccupancymanager.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;

import static java.lang.String.valueOf;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
abstract class BasicIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    protected ResultActions getOffer() throws Exception {
        return mockMvc.perform(get("/offers")
                .accept(APPLICATION_JSON));
    }

    protected ResultActions addOffer(double price) throws Exception {
        return mockMvc.perform(post("/offers")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new OfferDto(price))));
    }

    protected ResultActions getBookingCalculation(int premiumRoomsCount, int economyRoomsCount) throws Exception {
        return mockMvc.perform(get("/booking-calculation")
                .queryParam("economy", valueOf(economyRoomsCount))
                .queryParam("premium", valueOf(premiumRoomsCount))
                .accept(APPLICATION_JSON));
    }
}
