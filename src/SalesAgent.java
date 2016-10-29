import Message.PaymentConfirmation;
import Message.PaymentRequest;
import Message.SeatRequest;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.stream.impl.fusing.Map;
import initMessage.SectionAgentReference;

import java.util.HashMap;

/**
 * Created by Remy on 14-10-2016.
 */
public class SalesAgent extends UntypedActor {

    private HashMap<String, ActorRef> sectionManagers;

    @Override
    public void onReceive(Object message) throws Throwable {

        if (message instanceof SeatRequest) {
            String section = ((SeatRequest) message).getSection();
            System.out.println("I got a message from fan, he wants ticket in this section: " + section);
            sectionManagers.get(section).tell(message,getSelf());
        }else if(message instanceof SectionAgentReference){
            sectionManagers= ((SectionAgentReference) message).getSectionAgent();
        }
    }
}
