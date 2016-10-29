import Message.SeatRequest;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Remy on 26-10-2016.
 */
public class SectionAgent extends UntypedActor
{

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private final Map<String, List<Seat>> sectionSeatHashMap = new HashMap<>();

    @Override
    public void onReceive(Object o) throws Throwable {
        if(o instanceof SeatRequest){
            System.out.println("Arrived");
        }
    }

    public SectionAgent() {
        //Populate the hash map
        ArrayList<Seat> northSectionSeats = new ArrayList<>();
        ArrayList<Seat> southSectionSeats = new ArrayList<>();
        ArrayList<Seat> seatSectionSeats = new ArrayList<>();
        ArrayList<Seat> StandingSectionSeats = new ArrayList<>();
        //sectionSeatHashMap.put()
    }

    //public void setAgent()


}
