package pl.makowski.roomoccupancymanager.offer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private static Logger logger = LoggerFactory.getLogger(OfferController.class);

    private OfferFacade offerFacade;

    public OfferController(OfferFacade offerFacade) {
        this.offerFacade = offerFacade;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(CREATED)
    public void addOffer(@RequestBody OfferDto offerDto) {
        logger.info("Saving offer: {}", offerDto.toString());
        offerFacade.save(offerDto);
    }

    @GetMapping(produces = "application/json")
    public List<OfferDto> getOffers() {
        return offerFacade.getOffers();
    }

}
