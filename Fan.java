import akka.actor.*;
import akka.japi.Creator;
import akka.stream.testkit.StreamTestKit;

/**
 * Created by Remy on 14-10-2016.
 */
public class Fan extends UntypedActor {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private final int number;

    public Fan(int number) {
        this.number = number;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        System.out.println("Received from " + getSender());
        System.out.println(getSelf());
    }
}