package pl.makowski.roomoccupancymanager.offer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OfferDto(@JsonProperty("price") double price) { }
