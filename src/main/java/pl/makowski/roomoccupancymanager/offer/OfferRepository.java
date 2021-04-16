package pl.makowski.roomoccupancymanager.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.makowski.roomoccupancymanager.offer.entity.Offer;

import java.util.UUID;

interface OfferRepository extends JpaRepository<Offer, UUID> {
}
