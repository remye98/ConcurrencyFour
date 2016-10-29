import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.impl.fusing.Map;
import initMessage.SalesAgentReference;
import initMessage.SectionAgentReference;

import java.util.ArrayList;
import java.util.HashMap;
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
    /////////////////////////////////////////////////C:\Users\Remy\Desktop\Project Persistent\app\build\outputs\apk//////////////////////////

    private ArrayList<String> section = new ArrayList<>();
    private ActorRef standingPit, salesagent, fan;

    private void run() {
        ActorSystem actorSystem = ActorSystem.create("SectionAgent");
        standingPit = actorSystem.actorOf(Props.create(SectionAgent.class), "STANDINGPIT");
        salesagent = actorSystem.actorOf(Props.create(SalesAgent.class), "SA1");
        fan = actorSystem.actorOf(Props.create(Fan.class), "FAN");

        HashMap<String , ActorRef> sectionRef = new HashMap<>();
        sectionRef.put("standingPit",standingPit);
        salesagent.tell(new SectionAgentReference(sectionRef), null);
        fan.tell(new SalesAgentReference(salesagent), null);

//        ActorRef fan = actorSystem.actorOf(Props.create(Fan.class), "fanCreator");
//        ActorRef fanPerson = actorSystem.actorOf(Props.create(new FanCreator(fan)), "fan");

//        ActorRef fan1 = actorSystem.actorOf(Props.create(Fan.class), "fan1");
//        fan1.tell("Hello", null);

        for (int i = 0; i < 1000; i++) {
           // ActorRef fan = actorSystem.actorOf(Props.create(Fan.class, i), "fan" + i);
            // fans.add(fan);
        }

//        tellAllFans("wefjwe");
    }
}

//    private void tellAllFans(String message) {
//        for (ActorRef fan : fans) {
//            fan.tell(message, null);
//        }
//
//    }
//
//}
