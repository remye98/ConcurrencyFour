import actor.Fan;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import concert.Concert;
import ticketagency.TicketAgency;

import java.util.ArrayList;

/**
 * Created by Remy on 26-10-2016.
 */
public class Main {

    private ActorSystem actorSystem;
    private Concert concert;
    private TicketAgency ticketAgency;

    public static void main(String[] args) {
        new Main().run();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    /////////////////////////////////////////////////C:\Users\Remy\Desktop\Project Persistent\app\build\outputs\apk//////////////////////////

    private ArrayList<ActorRef> fans = new ArrayList<>();

    private void run() {
        actorSystem = ActorSystem.create("Concert");
        concert = new Concert();
        ticketAgency = new TicketAgency(actorSystem, concert);

        generateFans();
    }

    private void generateFans() {
        for (int id = 0; id < 10; id++) {
            ActorRef actorRef = actorSystem.actorOf(Props.create(Fan.class, concert, ticketAgency, id), "fan_" + id);
            fans.add(actorRef);
        }
    }
}