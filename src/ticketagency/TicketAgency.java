package ticketagency;

import actor.SalesAgent;
import actor.SectionAgent;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import concert.Concert;
import message.TicketRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public class TicketAgency {
    private static final Random random = new Random();
    public static final int MAX_AMOUNT_OF_SEAT_PER_FAN = 4;
    public static final int AMOUNT_OF_AGENTS = Concert.SectionType.values().length;

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private final ActorSystem actorSystem;
    private final Concert concert;
    private ArrayList<ActorRef> salesAgents = new ArrayList<>();
    private final HashMap<Concert.SectionType, ActorRef> sectionAgents = new HashMap<>();

    public TicketAgency(ActorSystem actorSystem, Concert concert) {
        this.actorSystem = actorSystem;
        this.concert = concert;

        generateAgent();
    }

    public void requestTicket(TicketRequest ticketRequest) {
        ActorRef salesAgent = salesAgents.get(random.nextInt(AMOUNT_OF_AGENTS));
        salesAgent.tell(ticketRequest, null);
    }

    public ActorRef getSectionAgent(Concert.SectionType sectionType) {
        return sectionAgents.get(sectionType);
    }

    private void generateAgent() {
        for (int i = 0; i < AMOUNT_OF_AGENTS; i++) {
            ActorRef actorRef =
                    actorSystem.actorOf(Props.create(SalesAgent.class, this), "sales_agent_" + i);

            salesAgents.add(actorRef);
        }

        for (int i = 0; i < Concert.SectionType.values().length; i++) {
            Concert.SectionType sectionType = Concert.SectionType.values()[i];
            ActorRef actorRef = actorSystem.actorOf(Props.create(SectionAgent.class, concert, sectionType), "section_agent_" + i);

            sectionAgents.put(sectionType, actorRef);
        }
    }
}
