import java.util.ArrayList;
import java.util.List;

/**
 * Created by Remy on 26-10-2016.
 */
public class Block {

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    private final int block;
    private final int amount;
    private List<Seat> seats = new ArrayList<>();

    public Block(int block, int amount) {
        this.block = block;
        this.amount = amount;
        for (int i = 0; i < 1000; i++) {
            //seats.add(new Seat());
        }
    }
}
