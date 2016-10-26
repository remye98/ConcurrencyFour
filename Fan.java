import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created by Remy on 14-10-2016.
 */
public class Fan extends UntypedActor {

    private final ActorRef actorRef;

    public Fan(ActorRef actorRef) {
        this.actorRef = actorRef;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
    }

    @Override
    public void onReceive(Object message) throws Throwable {


    }
}