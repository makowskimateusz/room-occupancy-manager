package pl.makowski.roomoccupancymanager.booking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.makowski.roomoccupancymanager.booking.dto.BookingCalculation;

@RestController
@RequestMapping("/booking-calculation")
class BookingCalculationController {

    private static final Logger logger = LoggerFactory.getLogger(BookingCalculationController.class);

    private BookingFacade bookingFacade;

    BookingCalculationController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody
    BookingCalculation calculate(@RequestParam("economy") int economyRoomsCount, @RequestParam("premium") int premiumRoomsCount) {
        logger.info("Calculating for economy rooms: {} and premium rooms: {}", economyRoomsCount, premiumRoomsCount);
        return bookingFacade.calculateRooms(premiumRoomsCount, economyRoomsCount);
    }

}
