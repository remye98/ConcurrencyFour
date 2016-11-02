package message;

import akka.actor.ActorRef;
import concert.Seat;

import java.util.List;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public class TicketResponse {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////
    private final List<Seat> seats;
    private final ActorRef to;

    /**
     * Constructor
     *
     * @param seats
     * @param to
     */
    public TicketResponse(List<Seat> seats, ActorRef to) {
        this.seats = seats;
        this.to = to;

    }

    public List<Seat> getSeats() {
        return seats;
    }
}
