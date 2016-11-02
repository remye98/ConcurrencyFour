package actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import concert.Concert;
import concert.Seat;
import concert.Section;
import message.TicketRequest;
import message.TicketResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remy on 26-10-2016.
 */
public class SectionAgent extends UntypedActor {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////
    private final Concert concert;
    private final Concert.SectionType sectionType;

    public SectionAgent(Concert concert, Concert.SectionType sectionType) {
        assert concert != null;
        assert sectionType != null;

        this.concert = concert;
        this.sectionType = sectionType;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        assert o != null;
        if (o instanceof TicketRequest) {
            TicketRequest request = (TicketRequest) o;
            Concert.SectionType sectionType = request.getSectionType();
            int amount = request.getAmountOfSeat();

            ActorRef from = request.getFrom();

            TicketResponse ticketResponse;
            List<Seat> seats = new ArrayList<>();
            if (concert.getSection(sectionType) instanceof Section.SeatingSection) {
                Section.SeatingSection section = (Section.SeatingSection) concert.getSection(sectionType);
                List<Seat> availableSeats = section.getAvailableSeats();

                if (availableSeats.size() > 0) {
                    if (amount > 1) {
                        seats.addAll(groupSeats(availableSeats, amount));
                        for (Seat seat : seats) {
                            seat.setReserved(true);
                        }
                    } else {
                        Seat seat = availableSeats.get(0);
                        seat.setReserved(true);
                        seats.add(seat);
                    }
                    ticketResponse = new TicketResponse(seats, from);
                } else {
                    ticketResponse = new TicketResponse(seats, from);
                }

                System.out.println(from.toString() + " reserved " + amount + " for " + sectionType);
                getSender().tell(ticketResponse, getSelf());
            } else {
                System.out.println();
            }

        }
    }

    private List<Seat> groupSeats(List<Seat> availableSeats, int amount) {
        List<Seat> consecutiveSeats = new ArrayList<>();

        int row = 0;
        int seatNr = 0;

        for (Seat availableSeat : availableSeats) {
            for (int i = 0; i < amount; i++) {
                if (consecutiveSeats.size() == 0) {
                    consecutiveSeats.add(availableSeat);
                } else {
                    Seat previousSeat = consecutiveSeats.get(consecutiveSeats.size() - 1);
                    Seat nextSeat = previousSeat.getNext();
                    boolean taken = nextSeat.isTaken();
                    boolean reserved = nextSeat.isReserved();

                    if (!taken || !reserved) {
                        consecutiveSeats.add(nextSeat);
                    } else {
                        consecutiveSeats.clear();
                    }
                }
            }

            if (consecutiveSeats.size() == amount) {
                break;
            }
        }

        return consecutiveSeats;
    }
}