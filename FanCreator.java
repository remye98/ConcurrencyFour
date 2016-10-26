import akka.actor.ActorRef;
import akka.japi.Creator;

/**
 * Created by Remy on 26-10-2016.
 */
public class FanCreator implements Creator<Fan> {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private final ActorRef actorRef;

    public FanCreator(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    @Override
    public Fan create() throws Exception {
        return new Fan(actorRef);

    }
}
