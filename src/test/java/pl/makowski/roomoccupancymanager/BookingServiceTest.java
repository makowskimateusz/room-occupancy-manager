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
        assertThat(result.premium()).isEqualTo(738);
        assertThat(result.economy()).isEqualTo(167.99);
    }


}
