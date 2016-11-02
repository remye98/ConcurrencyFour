package concert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by e_voe_000 on 10/30/2016.
 */
public abstract class Section {

    public Section() {

    }

    public static class SeatingSection extends Section {

        private final int rows;
        private final int seats;

        private HashMap<Integer, List<Seat>> map = new HashMap<>();

        public SeatingSection(int rows, int seats) {
            this.rows = rows;
            this.seats = seats;

            generateSeats();
        }

        public Map getSeats() {
            return map;
        }

        private void generateSeats() {
            for (int row = 0; row < rows; row++) {
                List<Seat> seats = new ArrayList<>();
                for (int seatNumber = 0; seatNumber < this.seats; seatNumber++) {
                    Seat seat = new Seat(row, seatNumber);

                    if (seats.size() > 0) {
                        Seat previousSeat = seats.get(seatNumber - 1);
                        previousSeat.setNext(seat);
                    }
                    seats.add(seat);
                }
                map.put(row, seats);
            }
        }

        public List<Seat> getAvailableSeats() {
            List<Seat> availableSeats = new ArrayList<>();
            for (List<Seat> seatList : map.values()) {
                for (Seat seat : seatList) {
                    if (!seat.isReserved() && !seat.isTaken()) {
                        availableSeats.add(seat);
                    }
                }
            }
            return availableSeats;
        }
    }

    public static class StandingSection extends Section {

        private final int allowed;
        private int count;

        public StandingSection(int allowed) {
            this.allowed = allowed;
        }
    }

}