package pl.makowski.roomoccupancymanager;

import java.util.ArrayDeque;
import java.util.function.DoublePredicate;
import java.util.stream.DoubleStream;

import static java.util.stream.Collectors.toCollection;

public class BookingService {

    private static final double[] clientsOffers = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
    private static final double PRICE_THRESHOLD = 100.00;

    public BookingCalculation calculateRooms(long premiumRoomsCount, long economyRoomsCount) {
        final var offersBelow100Eur = getCollect(below100Eur());
        final var offersAbove100Eur = getCollect(above100Eur());

        final var economyIncome = countIncome(economyRoomsCount, offersBelow100Eur);
        final var premiumIncome = countIncome(premiumRoomsCount, offersAbove100Eur);

        return new BookingCalculation(economyIncome, premiumIncome);
    }

    private ArrayDeque<Double> getCollect(DoublePredicate priceLevelPredicate) {
        return DoubleStream.of(clientsOffers)
                .sorted()
                .filter(priceLevelPredicate)
                .boxed()
                .collect(toCollection(ArrayDeque::new));
    }

    private DoublePredicate above100Eur() {
        return offer -> offer > PRICE_THRESHOLD;
    }

    private DoublePredicate below100Eur() {
        return offer -> offer < PRICE_THRESHOLD;
    }

    private double countIncome(long roomsCount, ArrayDeque<Double> offers) {
        double sum = 0;
        for (int i = 0; i < roomsCount; i++) {
            sum = sum + offers.pollLast();
        }
        return sum;
    }

}

