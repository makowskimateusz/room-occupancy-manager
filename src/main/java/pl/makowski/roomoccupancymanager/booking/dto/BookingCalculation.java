package pl.makowski.roomoccupancymanager.booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BookingCalculation(@JsonProperty("economy") Double economy, @JsonProperty("premium") Double premium) {
}
