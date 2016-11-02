package message;

import akka.actor.ActorRef;
import akka.camel.internal.component.ActorEndpoint;
import akka.cluster.ddata.Replicator;
import concert.Concert;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public class TicketRequest {

    private final Concert.SectionType sectionType;
    private final int amountOfSeat;
    private final ActorRef from;

    public TicketRequest(Concert.SectionType sectionType, int amountOfSeat, ActorRef from) {
        this.sectionType = sectionType;
        this.amountOfSeat = amountOfSeat;
        this.from = from;
    }

    public int getAmountOfSeat() {
        return amountOfSeat;
    }

    public Concert.SectionType getSectionType() {
        return sectionType;
    }

    public ActorRef getFrom() {
        return from;
    }

}
