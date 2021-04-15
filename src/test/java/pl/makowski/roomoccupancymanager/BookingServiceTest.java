package pl.makowski.roomoccupancymanager;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingServiceTest {

    private final BookingService bookingService = new BookingService();

    @Test
    public void shouldReturnProposedIncomeForEconomyAndPremiumRooms() {
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
    public void shouldReturnProposedIncomeForEconomyAndPremiumRooms2() {
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
    public void shouldReturnProposedIncomeForEconomyAndPremiumRooms3() {
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
    public void shouldReturnProposedIncomeForEconomyAndPremiumRooms4() {
        //given
        final long premiumRoomsCount = 7;
        final long economyRoomsCount = 1;
        //when
        final var result = bookingService.calculateRooms(premiumRoomsCount, economyRoomsCount);
        //then
        assertThat(result.premium()).isEqualTo(1153.99);
        assertThat(result.economy()).isEqualTo(45);
    }
}
