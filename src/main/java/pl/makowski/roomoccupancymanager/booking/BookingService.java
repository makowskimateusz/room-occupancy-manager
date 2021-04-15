package pl.makowski.roomoccupancymanager.booking;

import pl.makowski.roomoccupancymanager.booking.dto.BookingCalculation;

import static pl.makowski.roomoccupancymanager.booking.entity.BookingType.ECONOMY;
import static pl.makowski.roomoccupancymanager.booking.entity.BookingType.PREMIUM;

public class BookingService {

    private static final double[] clientsOffers = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};

    private final BookingCreator bookingCreator = new BookingCreator();
    private final IncomeCalculator incomeCalculator = new IncomeCalculator();
    private final BookingUpgrader bookingUpgrader = new BookingUpgrader();

    public BookingCalculation calculateRooms(long premiumRoomsCount, long economyRoomsCount) {
        final var premiumBookings = bookingCreator.createBookings(clientsOffers, PREMIUM);
        final var economyBookings = bookingCreator.createBookings(clientsOffers, ECONOMY);

        final var premiumAndUpgradedBookings = bookingUpgrader.upgradeBookings(premiumRoomsCount, premiumBookings, economyRoomsCount, economyBookings);

        double premiumIncome = incomeCalculator.calculate(premiumRoomsCount, premiumAndUpgradedBookings);
        double economyIncome = incomeCalculator.calculate(economyRoomsCount, economyBookings);

        return new BookingCalculation(economyIncome, premiumIncome);
    }
}


