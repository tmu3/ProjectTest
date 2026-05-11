package app.service;

import app.dto.BidRequest;
import app.dto.BidResponse;
import org.springframework.stereotype.Service;
import app.model.Auction;
import app.model.User;
import app.repository.AuctionRepository;
import app.repository.UserRepository;

@Service
public class BidService {
    private final AuctionRepository auctionRepository;

    private final UserRepository userRepository;

    public BidService(
            AuctionRepository auctionRepository,
            UserRepository userRepository
    ) {
        this.auctionRepository = auctionRepository;
        this.userRepository = userRepository;
    }

    public synchronized BidResponse placeBid(
            BidRequest request
    ) {
        Auction auction =
                auctionRepository
                        .findById(request.getAuctionId())
                        .orElse(null);

        if (auction == null) {
            return new BidResponse(
                    false,
                    "Auction not found",
                    0,
                    null
            );
        }

        User bidder =
                userRepository
                        .findById(request.getBidderId())
                        .orElse(null);

        if (bidder == null) {
            return new BidResponse(
                    false,
                    "Bidder not found",
                    0,
                    null
            );
        }

        boolean success =
                auction.placeBid(
                        bidder,
                        request.getAmount()
                );

        if (!success) {
            return new BidResponse(
                    false,
                    "Bid failed",
                    auction.getHighestBid(),
                    auction.getHighestBidderId()
            );
        }

        auctionRepository.save(auction);
        return new BidResponse(
                true,
                "Bid success",
                auction.getHighestBid(),
                auction.getHighestBidderId()
        );
    }
}
