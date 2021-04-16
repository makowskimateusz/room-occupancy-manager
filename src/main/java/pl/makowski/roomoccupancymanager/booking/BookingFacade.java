package pl.makowski.roomoccupancymanager.booking;

import pl.makowski.roomoccupancymanager.booking.dto.BookingCalculation;
import pl.makowski.roomoccupancymanager.offer.OfferFacade;

import static pl.makowski.roomoccupancymanager.booking.entity.BookingType.ECONOMY;
import static pl.makowski.roomoccupancymanager.booking.entity.BookingType.PREMIUM;

public class BookingFacade {

    private final BookingCreator bookingCreator;
    private final IncomeCalculator incomeCalculator;
    private final BookingUpgrader bookingUpgrader;
    private final OfferFacade offerFacade;

    public BookingFacade(BookingCreator bookingCreator,
                         IncomeCalculator incomeCalculator,
                         BookingUpgrader bookingUpgrader,
                         OfferFacade offerFacade) {
        this.bookingCreator = bookingCreator;
        this.incomeCalculator = incomeCalculator;
        this.bookingUpgrader = bookingUpgrader;
        this.offerFacade = offerFacade;
    }

    public BookingCalculation calculateRooms(long premiumRoomsCount, long economyRoomsCount) {
        final var offers = offerFacade.getOffers();
        final var premiumBookings = bookingCreator.createBookings(offers, PREMIUM);
        final var economyBookings = bookingCreator.createBookings(offers, ECONOMY);

        final var premiumAndUpgradedBookings = bookingUpgrader.upgradeBookings(premiumRoomsCount, premiumBookings, economyRoomsCount, economyBookings);

        double premiumIncome = incomeCalculator.calculate(premiumRoomsCount, premiumAndUpgradedBookings);
        double economyIncome = incomeCalculator.calculate(economyRoomsCount, economyBookings);

        return new BookingCalculation(economyIncome, premiumIncome);
    }
}


