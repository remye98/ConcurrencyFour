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

            if (concert.getSection(sectionType) instanceof Section.SeatingSection) {
                Section.SeatingSection section = (Section.SeatingSection) concert.getSection(sectionType);
                List<Seat> availableSeats = section.getAvailableSeats();

                List<Seat> seats = new ArrayList<>();
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

                TicketResponse ticketResponse = new TicketResponse(seats, from);
                System.out.println(from.toString() + " reserved " + amount + " for " + sectionType);
                for (Seat seat : ticketResponse.getSeats()) {
                    System.out.print(seat.toString() + " ");
                }
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

        for (int i = 0; i < availableSeats.size(); i++) {
            row = availableSeats.get(i).getRow();
            seatNr = availableSeats.get(i).getSeatNumber();
            for (int j = i + 1; j < amount; j++) {
                if (row == availableSeats.get(j).getRow()) {
                    if ((seatNr - availableSeats.get(j).getSeatNumber()) == -1) {
                        consecutiveSeats.add(availableSeats.get(j));
                    } else {
                        consecutiveSeats.clear();
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return consecutiveSeats;
    }
}