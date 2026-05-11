package app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Bidder extends User{
    private double balance;
    private List<String> watchlist;
    @OneToMany(mappedBy = "bidder")
    private List<BidTransaction> bidHistory;

    public Bidder() {}

    public Bidder(String userName, String password, String email, String fullName) {
        super(userName, password, email, fullName);
    }

    protected List<String> getWatchlist(){
        return watchlist;
    }

    protected List<BidTransaction> getBidHistory(){
        return bidHistory;
    }

    public double getBalance() {
        return balance;
    }

    public void reduce(double amount) {
        balance -= amount;
    }
}
