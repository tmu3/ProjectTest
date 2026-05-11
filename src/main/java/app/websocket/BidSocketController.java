package app.websocket;

import app.dto.BidRequest;
import app.dto.BidResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import app.service.BidService;

@Controller
public class BidSocketController {
    private final BidService bidService;

    private final SimpMessagingTemplate messagingTemplate;

    public BidSocketController(
            BidService bidService,
            SimpMessagingTemplate messagingTemplate) {

        this.bidService = bidService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/bid")
    public void handleBid(BidRequest request) {

        BidResponse response =
                bidService.placeBid(request);

        messagingTemplate.convertAndSend(
                "/topic/auction/" + request.getAuctionId(),
                response
        );
    }
}
