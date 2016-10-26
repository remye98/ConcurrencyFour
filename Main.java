import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remy on 26-10-2016.
 */
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private List<ActorRef> fans = new ArrayList<>();

    private void run() {
        ActorSystem actorSystem = ActorSystem.create("Concert");
//        ActorRef fan = actorSystem.actorOf(Props.create(Fan.class), "fanCreator");
//        ActorRef fanPerson = actorSystem.actorOf(Props.create(new FanCreator(fan)), "fan");

//        ActorRef fan1 = actorSystem.actorOf(Props.create(Fan.class), "fan1");
//        fan1.tell("Hello", null);

        for (int i = 0; i < 1000; i++) {
            ActorRef fan = actorSystem.actorOf(Props.create(Fan.class, i), "fan" + i);
            fans.add(fan);
        }

        tellAllFans("wefjwe");
    }

    private void tellAllFans(String message) {
        for (ActorRef fan : fans) {
            fan.tell(message, null);
        }

    }

}
