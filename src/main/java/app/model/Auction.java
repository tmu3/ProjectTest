package app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Auction extends BaseEntity {
    @OneToOne
    private Item item;
    @ManyToOne
    private Seller seller;
    @Enumerated(EnumType.STRING)
    private AuctionStatus auctionStatus;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BidTransaction> bids = new ArrayList<>();
    private LocalDateTime endTime;
    private LocalDateTime startTime;
    private double highestBid = 0;
    @ManyToOne
    private Bidder highestBidder;

    public Auction() {}

    public Auction(Item item, Seller seller, LocalDateTime endTime) {
        this.item = item;
        this.seller = seller;
        this.endTime = endTime;
        this.auctionStatus = AuctionStatus.OPEN;
    }

    public synchronized boolean placeBid(User bidder, double amount) {

        if (auctionStatus == AuctionStatus.FINISHED
                || auctionStatus == AuctionStatus.CANCELED
                || LocalDateTime.now().isAfter(endTime)) {
            return false;
        }

        if (amount <= highestBid) {
            return false;
        }

        Bidder realBidder = (Bidder) bidder;

        if (realBidder.getBalance() < amount) {
            return false;
        }

        highestBid = amount;
        highestBidder = realBidder;

        bids.add(new BidTransaction(realBidder, amount));

        return true;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public String getHighestBidderId() {
        return highestBidder != null ? highestBidder.getId() : null;
    }
}
