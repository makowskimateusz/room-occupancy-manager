package pl.makowski.roomoccupancymanager.offer;

import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;
import pl.makowski.roomoccupancymanager.offer.domain.Offer;

class OfferMapper {
    static Offer map(OfferDto offerDto) {
        final var offer = new Offer();
        offer.setPrice(offerDto.price());
        return offer;
    }

    static OfferDto map(Offer offer) {
        return new OfferDto(offer.getPrice());
    }
}
