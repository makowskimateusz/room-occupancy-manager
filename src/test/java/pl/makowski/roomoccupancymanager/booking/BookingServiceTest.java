package pl.makowski.roomoccupancymanager.booking;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingServiceTest {

    private final BookingService bookingService = new BookingService();

    @Test
    public void shouldCalculateIncomeWithoutUpgradingOffers() {
        //given
        final long premiumRoomsCount = 3;
        final long economyRoomsCount = 3;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.economy()).isEqualTo(167.99);
        assertThat(result.premium()).isEqualTo(738);
    }

    @Test
    public void shouldCalculateIncomeWithoutUpgradingOffersWhenThereIsNoUpgradeCandidates() {
        //given
        final long premiumRoomsCount = 7;
        final long economyRoomsCount = 5;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(1054);
        assertThat(result.economy()).isEqualTo(189.99);
    }

    @Test
    public void shouldNotCalculateIncomeIncludedUpgradesWhenForEconomyOffers() {
        //given
        final long premiumRoomsCount = 2;
        final long economyRoomsCount = 7;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(583);
        assertThat(result.economy()).isEqualTo(189.99);
    }

    @Test
    public void shouldCalculateIncomeIncludingUpgrades() {
        //given
        final long premiumRoomsCount = 7;
        final long economyRoomsCount = 1;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(1153.99);
        assertThat(result.economy()).isEqualTo(45);
    }

    @Test
    public void shouldCalculateIncomeEvenIfNoEconomyBookingsRequested() {
        //given
        final long premiumRoomsCount = 7;
        final long economyRoomsCount = 0;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(1153.99);
        assertThat(result.economy()).isEqualTo(0);
    }

    @Test
    public void shouldCalculateIncomeEvenIfNoPremiumBookingsRequested() {
        //given
        final long premiumRoomsCount = 0;
        final long economyRoomsCount = 3;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(0);
        assertThat(result.economy()).isEqualTo(167.99);
    }

    @Test
    public void shouldCalculateZeroIncomeWhenNoRoomsWanted() {
        //given
        final long premiumRoomsCount = 0;
        final long economyRoomsCount = 0;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(0);
        assertThat(result.economy()).isEqualTo(0);
    }
}
