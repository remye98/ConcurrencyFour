package concert;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public class Seat {

    private final int row;
    private final int seatNumber;

    private boolean reserved = false;
    private boolean taken = false;

    private Seat next;

    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;

    }

    public boolean isReserved() {
        return reserved;
    }

    public boolean isTaken() {
        return taken;
    }

    public Seat getNext() {
        return next;
    }

    public void setNext(Seat next) {
        this.next = next;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getRow() {
        return row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return row + " " + seatNumber;
    }
}
