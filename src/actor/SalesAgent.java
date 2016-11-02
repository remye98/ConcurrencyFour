package actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import concert.Concert;
import message.TicketRequest;
import ticketagency.TicketAgency;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public class SalesAgent extends UntypedActor {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////
    private final TicketAgency ticketAgency;

    public SalesAgent(TicketAgency ticketAgency) {
        this.ticketAgency = ticketAgency;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof TicketRequest) {
            TicketRequest request = (TicketRequest) o;
            Concert.SectionType sectionType = request.getSectionType();
            ActorRef sectionAgent = ticketAgency.getSectionAgent(sectionType);
            sectionAgent.tell(request, getSender());
        }
    }
}
