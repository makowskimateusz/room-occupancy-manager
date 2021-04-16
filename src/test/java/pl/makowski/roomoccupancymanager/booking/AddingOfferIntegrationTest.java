package pl.makowski.roomoccupancymanager.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class AddingOfferIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    private ResultActions getOffer() throws Exception {
        return mockMvc.perform(get("/offers")
                .accept(APPLICATION_JSON));
    }

    private ResultActions addOffer(double price) throws Exception {
        return mockMvc.perform(post("/offers")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new OfferDto(price))));
    }

}
