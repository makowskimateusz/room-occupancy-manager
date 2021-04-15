package pl.makowski.roomoccupancymanager;

import java.util.ArrayDeque;

public class IncomeCalculator {

    double calculate(long roomCount, ArrayDeque<Booking> bookings) {
        double income = 0;

        for (int i = 0; i < roomCount; i++) {
            final var booking = bookings.pollLast();
            if (booking != null) {
                income = income + booking.getPrice();
            }
        }
        return income;
    }
}