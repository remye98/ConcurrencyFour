package Message;

import akka.actor.ActorRef;

/**
 * Created by Remy on 28-10-2016.
 */
public class SeatRequest {

    private String section;
    private int amountTickets;
    private ActorRef actorRef;

    public SeatRequest(String section, int amountTickets, ActorRef actorRef) {
        this.section = section;
        this.amountTickets = amountTickets;
        this.actorRef = actorRef;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getAmountTickets() {
        return amountTickets;
    }

    public void setAmountTickets(int amountTickets) {
        this.amountTickets = amountTickets;
    }

    public ActorRef getActorRef() {
        return actorRef;
    }

    public void setActorRef(ActorRef actorRef) {
        this.actorRef = actorRef;
    }
}
