package app.repository;

import app.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository
        extends JpaRepository<Auction, String> {
}
