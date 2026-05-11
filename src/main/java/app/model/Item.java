package app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item extends BaseEntity {
    private String name;
    private String description;
    private double startPrice;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ItemCategory category;
    @ManyToOne
    private Seller seller;
    private double currentHighestBid;
    private LocalDateTime startBid;
    private LocalDateTime endBid;
    private boolean available;
    @OneToMany(mappedBy = "item")
    private List<BidTransaction> bids;

    public Item() {}

    protected Item(String name, String description, double startingPrice, String imageUrl, Seller seller) {
        this.name = name;
        this.description = description;
        this.startPrice = startingPrice;
        this.imageUrl = imageUrl;
        this.seller = seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
