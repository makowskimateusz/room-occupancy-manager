package pl.makowski.roomoccupancymanager;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static pl.makowski.roomoccupancymanager.BookingType.ECONOMY;
import static pl.makowski.roomoccupancymanager.BookingType.PREMIUM;

enum BookingType {
    PREMIUM, ECONOMY
}

public class BookingService {

    private static final double[] clientsOffers = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
    private static final double PRICE_THRESHOLD = 100.00;

    public BookingCalculation calculateRooms(long premiumRoomsCount, long economyRoomsCount) {
        final List<Booking> bookings = categorizeOffersByBookingType();

        final var premiumBookings = getBookingsByType(bookings, PREMIUM);
        final var economyBookings = getBookingsByType(bookings, ECONOMY);

        ArrayDeque<Booking> premiumAndUpgradedBookings = tryUpgradeEconomyBookingsToSatisfyRequirments(premiumRoomsCount, economyRoomsCount, premiumBookings, economyBookings);

        double premiumIncome = calculateIncome(premiumRoomsCount, premiumAndUpgradedBookings);
        double economyIncome = calculateIncome(economyRoomsCount, economyBookings);

        return new BookingCalculation(economyIncome, premiumIncome);
    }

    private double calculateIncome(long roomCount, ArrayDeque<Booking> bookings) {
        double income = 0;

        for (int i = 0; i < roomCount; i++) {
            final var booking = bookings.pollLast();
            if (booking != null) {
                income = income + booking.getPrice();
            }
        }
        return income;
    }

    private ArrayDeque<Booking> tryUpgradeEconomyBookingsToSatisfyRequirments(long premiumRoomsCount, long economyRoomsCount, ArrayDeque<Booking> premiumBookings, ArrayDeque<Booking> economyBookings) {
        ArrayDeque<Booking> premiumAndUpgradedBookings = new ArrayDeque<>();
        premiumAndUpgradedBookings.addAll(premiumBookings);
        if (premiumBookings.size() < premiumRoomsCount && !(economyRoomsCount > economyBookings.size())) {
            for (int i = 0; i < premiumRoomsCount - premiumBookings.size(); i++) {
                final var upgradedBooking = economyBookings.pollLast();
                if (upgradedBooking != null) {
                    premiumAndUpgradedBookings.addFirst(upgradedBooking);
                }
            }
        }
        return premiumAndUpgradedBookings;
    }

    private ArrayDeque<Booking> getBookingsByType(List<Booking> bookings, BookingType bookingType) {
        return bookings
                .stream()
                .filter(booking -> booking.getType().equals(bookingType))
                .collect(toCollection(ArrayDeque::new));
    }

    private List<Booking> categorizeOffersByBookingType() {
        return DoubleStream.of(clientsOffers)
                .sorted()
                .mapToObj(clientOffer -> {
                    if (clientOffer < PRICE_THRESHOLD) {
                        return new Booking(clientOffer, ECONOMY);
                    } else {
                        return new Booking(clientOffer, PREMIUM);
                    }
                }).collect(toList());
    }

}


