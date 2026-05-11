package app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class BidTransaction extends BaseEntity {
    @ManyToOne
    private Bidder bidder;
    private double amount;
    private LocalDateTime time;
    @ManyToOne
    private Item item;

    public BidTransaction() {}

    public BidTransaction(Bidder bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        if (time == null) {
            time = LocalDateTime.now();
        }
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
