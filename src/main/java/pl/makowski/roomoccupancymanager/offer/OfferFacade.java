package pl.makowski.roomoccupancymanager.offer;

import pl.makowski.roomoccupancymanager.offer.dto.OfferDto;

import java.util.List;

import static pl.makowski.roomoccupancymanager.offer.OfferMapper.map;


public class OfferFacade {

    private OfferRepository offerRepository;

    public OfferFacade(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void save(OfferDto offerDto) {
        offerRepository.save(map(offerDto));
    }

    public List<OfferDto> getOffers() {
        return offerRepository.findAll()
                .stream()
                .map(OfferMapper::map)
                .toList();
    }

}
