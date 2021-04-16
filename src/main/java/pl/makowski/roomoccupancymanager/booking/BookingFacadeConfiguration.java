package pl.makowski.roomoccupancymanager.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.makowski.roomoccupancymanager.offer.OfferFacade;
import pl.makowski.roomoccupancymanager.offer.OfferFacadeConfiguration;

@Configuration
class BookingFacadeConfiguration {

    @Bean
    public BookingFacade bookingFacade(OfferFacade offerFacade) {
        final var bookingCreator = new BookingCreator();
        final var incomeCalculator = new IncomeCalculator();
        final var bookingUpgrader = new BookingUpgrader();
        return new BookingFacade(bookingCreator, incomeCalculator, bookingUpgrader, offerFacade);
    }

}
