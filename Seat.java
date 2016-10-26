import akka.actor.ActorRef;

/**
 * Created by Remy on 26-10-2016.
 */
public class Seat {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private final int seatNr;
    private final int block;
    private final int row;
    private final int price;

    private ActorRef actorRef;

    public Seat(int seatNr, int block, int row, int price) {
        this.seatNr = seatNr;
        this.block = block;
        this.row = row;
        this.price = price;
    }

    public void setActorRef(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    public ActorRef getActorRef() {
        return actorRef;
    }

    /*
north
15000
 7500 999,-
 7500 1599,-

south
15000
 7500 999,-
 7500 1599,-

general
15000 399,-

standing
15000 1599,-

middle
15000
 5000 699,-
 5000 999,-
 5000 1599,-
 */

}
