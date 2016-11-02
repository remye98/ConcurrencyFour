import actor.Fan;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import concert.Concert;
import concert.Seat;
import concert.Section;
import ticketagency.TicketAgency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        while (true) {
            ArrayList<Section> sections = new ArrayList<>();
            for (Concert.SectionType sectionType : Concert.SectionType.values()) {
                Section section = concert.getSection(sectionType);
                sections.add(section);
            }

            ArrayList<Map> seats = new ArrayList<>();
            for (Section section : sections) {
                if (section instanceof Section.SeatingSection) {
                    Map map = ((Section.SeatingSection) section).getSeats();
                    seats.add(map);
                }
            }

//            Testing purposes
            for (Map map : seats) {
                Set set = map.keySet();
                for (Object row : set) {
                    int rowInteger = (Integer) row;
                    Object seatList = map.get(rowInteger);
                    if (seatList instanceof List) {
                        Seat o = ((Seat) ((List) seatList).get(rowInteger));
                        if (o.isReserved() || o.isTaken()) {
                            System.out.println();
                        }
                    }
                }
            }

            System.out.println();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateFans() {
        for (int id = 0; id < 75000; id++) {
            ActorRef actorRef = actorSystem.actorOf(Props.create(Fan.class, concert, ticketAgency, id), "fan_" + id);
            fans.add(actorRef);
        }
    }
}