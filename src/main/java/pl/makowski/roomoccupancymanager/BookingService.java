package pl.makowski.roomoccupancymanager;

import java.util.ArrayDeque;
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
        final var bookings = DoubleStream.of(clientsOffers)
                .sorted()
                .mapToObj(clientOffer -> {
                    if (clientOffer < PRICE_THRESHOLD) {
                        return new Booking(clientOffer, ECONOMY);
                    } else {
                        return new Booking(clientOffer, PREMIUM);
                    }
                }).collect(toList());

        final var premiumBookings = bookings.stream().filter(booking -> booking.getType().equals(PREMIUM)).collect(toCollection(ArrayDeque::new));
        final var economyBookings = bookings.stream().filter(booking -> booking.getType().equals(ECONOMY)).collect(toCollection(ArrayDeque::new));

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

        double premiumIncome = 0;

        for (int i = 0; i < premiumRoomsCount; i++) {
            final var booking = premiumAndUpgradedBookings.pollLast();
            if (booking != null) {
                premiumIncome = premiumIncome + booking.getPrice();
            }
        }

        double economyIncome = 0;

        for (int i = 0; i < economyRoomsCount; i++) {
            final var booking = economyBookings.pollLast();
            if (booking != null) {
                economyIncome = economyIncome + booking.getPrice();
            }
        }

        return new BookingCalculation(economyIncome, premiumIncome);
    }

}

class Booking implements Comparable<Booking> {

    private final Double price;
    private final BookingType type;

    public Booking(double price, BookingType type) {
        this.price = price;
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public BookingType getType() {
        return type;
    }

    @Override
    public int compareTo(Booking booking) {
        return booking.price.compareTo(this.price);
    }
}


