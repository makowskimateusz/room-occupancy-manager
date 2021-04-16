package pl.makowski.roomoccupancymanager.booking;

import pl.makowski.roomoccupancymanager.booking.entity.Booking;
import pl.makowski.roomoccupancymanager.booking.entity.BookingType;
import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;

import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.DoubleStream.*;
import static pl.makowski.roomoccupancymanager.booking.entity.BookingType.ECONOMY;
import static pl.makowski.roomoccupancymanager.booking.entity.BookingType.PREMIUM;

class BookingCreator {

    private static final double PRICE_THRESHOLD = 100.00;

    ArrayDeque<Booking> createBookings(List<OfferDto> offers, BookingType bookingType) {
        return categorizeOffersByBookingType(offers)
                .stream()
                .filter(booking -> booking.getType().equals(bookingType))
                .collect(toCollection(ArrayDeque::new));
    }

    private List<Booking> categorizeOffersByBookingType(List<OfferDto> offers) {
        return offers.stream()
                .flatMapToDouble(offer -> of(offer.price()))
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