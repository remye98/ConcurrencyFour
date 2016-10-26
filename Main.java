import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created by Remy on 26-10-2016.
 */
public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        ActorSystem actorSystem = ActorSystem.create("Concert");
        ActorRef fan = actorSystem.actorOf(Props.create(Fan.class), "fan");
    }

}
