package app.controller;

import app.dto.BidRequest;
import app.dto.BidResponse;
import org.springframework.web.bind.annotation.*;
import app.service.BidService;

@RestController
@RequestMapping("/api/bids")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public BidResponse placeBid(
            @RequestBody BidRequest request
    ) {

        return bidService.placeBid(request);
    }
}
