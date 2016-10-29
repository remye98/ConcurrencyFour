import Message.SeatRequest;
import akka.actor.*;
import akka.japi.Creator;
import akka.stream.testkit.StreamTestKit;
import initMessage.SalesAgentReference;

/**
 * Created by Remy on 14-10-2016.
 */
public class Fan extends UntypedActor {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////



    private ActorRef salesagent;


    @Override
    public void preStart() throws Exception {
        super.preStart();
    }

    private void initMessage() {
        String standingPit = "standingPit";
        int ticketAmount = 2;
        SeatRequest seatRequest = new SeatRequest(standingPit, ticketAmount, getSelf());
        salesagent.tell(seatRequest, getSelf());
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        if(message instanceof SalesAgentReference){
            System.out.println("helloooooo");
            salesagent = ((SalesAgentReference) message).getSalesAgent();
            initMessage();
        }
    }
}