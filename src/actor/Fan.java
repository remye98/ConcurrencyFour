package actor;

import akka.actor.UntypedActor;
import concert.Concert;
import message.TicketRequest;
import message.TicketResponse;
import ticketagency.TicketAgency;

import java.util.Random;

/**
 * Created by Remy on 14-10-2016.
 */
public class Fan extends UntypedActor {

    private static final Random random = new Random();

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////
    private final Concert concert;
    private final TicketAgency ticketAgency;
    private final int id;


    public Fan(Concert concert, TicketAgency ticketAgency, int id) {
        this.concert = concert;
        this.ticketAgency = ticketAgency;
        this.id = id;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();

        //Generate random decision

        //Random section
        int sortCount = Concert.SectionType.values().length;
        Concert.SectionType sectionType =
                Concert.SectionType.values()[random.nextInt(sortCount)];

        //Random amount of Seat
        int amountOfSeat = random.nextInt(TicketAgency.MAX_AMOUNT_OF_SEAT_PER_FAN);

        System.out.println(id + " attempting to buy ticket for " + amountOfSeat + " seats on " + sectionType);

        TicketRequest ticketRequest = new TicketRequest(sectionType, amountOfSeat, getSelf());
        ticketAgency.requestTicket(ticketRequest);
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof TicketResponse) {
            TicketResponse ticketResponse = (TicketResponse) o;

            if (ticketResponse.getSeats().size() > 0) {
                System.out.println("Ma haya tickets");
            } else {
                System.out.println("Rejected");
            }
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}