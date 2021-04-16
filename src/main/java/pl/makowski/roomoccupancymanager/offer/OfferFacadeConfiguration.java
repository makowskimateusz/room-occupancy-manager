package pl.makowski.roomoccupancymanager.offer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferFacadeConfiguration {

    @Bean
    public OfferFacade offerFacade(OfferRepository offerRepository) {
        return new OfferFacade(offerRepository);
    }

    public OfferFacade offerFacade() {
        return new OfferFacade(new InMemoryOfferRepository());
    }
}
