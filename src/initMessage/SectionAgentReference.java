package initMessage;

import akka.actor.ActorRef;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Remy on 28-10-2016.
 */
public class SectionAgentReference {

    private HashMap<String, ActorRef> sectionAgent;

    public SectionAgentReference(HashMap<String, ActorRef> sectionAgent) {
        this.sectionAgent = sectionAgent;
    }

    public HashMap<String, ActorRef> getSectionAgent() {
        return sectionAgent;
    }
}
