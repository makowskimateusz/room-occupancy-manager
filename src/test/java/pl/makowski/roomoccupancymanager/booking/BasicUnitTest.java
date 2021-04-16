package pl.makowski.roomoccupancymanager.booking;

import pl.makowski.roomoccupancymanager.offer.OfferFacade;
import pl.makowski.roomoccupancymanager.offer.OfferFacadeConfiguration;
import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;

import java.util.stream.DoubleStream;

abstract class BasicUnitTest {
    private final OfferFacade offerFacade = new OfferFacadeConfiguration().offerFacade();
    protected final BookingFacade bookingFacade = new BookingFacadeConfiguration().bookingFacade(offerFacade);

    protected void addSampleOffers() {
        final double[] offers = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
        DoubleStream.of(offers).forEach(offer -> offerFacade.save(new OfferDto(offer)));
    }
}
