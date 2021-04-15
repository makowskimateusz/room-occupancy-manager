package pl.makowski.roomoccupancymanager;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static pl.makowski.roomoccupancymanager.BookingType.ECONOMY;
import static pl.makowski.roomoccupancymanager.BookingType.PREMIUM;

public class BookingCreator {

    private static final double PRICE_THRESHOLD = 100.00;

    ArrayDeque<Booking> createBookings(double[] offers, BookingType bookingType) {
        return categorizeOffersByBookingType(offers)
                .stream()
                .filter(booking -> booking.getType().equals(bookingType))
                .collect(toCollection(ArrayDeque::new));
    }

    private List<Booking> categorizeOffersByBookingType(double[] offers) {
        return DoubleStream.of(offers)
                .sorted()
                .mapToObj(offer -> {
                    if (offer < PRICE_THRESHOLD) {
                        return new Booking(offer, ECONOMY);
                    } else {
                        return new Booking(offer, PREMIUM);
                    }
                }).collect(toList());
    }
}