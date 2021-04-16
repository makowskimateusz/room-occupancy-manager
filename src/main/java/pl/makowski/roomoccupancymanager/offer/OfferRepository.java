package pl.makowski.roomoccupancymanager.offer;

import org.springframework.data.repository.Repository;
import pl.makowski.roomoccupancymanager.offer.entity.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

interface OfferRepository extends Repository<Offer, UUID> {
    void save(Offer offer);

    List<Offer> findAll();
}

class InMemoryOfferRepository implements OfferRepository {

    private final List<Offer> database = new ArrayList<>();

    @Override
    public void save(Offer offer) {
        database.add(offer);
    }

    @Override
    public List<Offer> findAll() {
        return database;
    }
}
