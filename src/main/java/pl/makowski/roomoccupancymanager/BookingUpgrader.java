package pl.makowski.roomoccupancymanager;

import java.util.ArrayDeque;

public class BookingUpgrader {
    public BookingUpgrader() {
    }

    ArrayDeque<Booking> upgradeBookings(long premiumRoomsCount, ArrayDeque<Booking> premiumBookings, long economyRoomsCount, ArrayDeque<Booking> economyBookings) {
        ArrayDeque<Booking> premiumAndUpgradedBookings = new ArrayDeque<Booking>();
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
}