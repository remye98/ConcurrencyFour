package initMessage;

import akka.actor.ActorRef;

/**
 * Created by Remy on 28-10-2016.
 */
public class SalesAgentReference {
    //in future this will become router..

    private ActorRef salesAgent;

    public ActorRef getSalesAgent() {
        return salesAgent;
    }

    public SalesAgentReference(ActorRef salesAgent) {

        this.salesAgent = salesAgent;
    }
}
