package cc.oobootcamp.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int totalSpace;
    private Map<Ticket, Car> parkedCars;

    public ParkingLot(int totalSpace) {
        this.totalSpace = totalSpace;
        this.parkedCars = new HashMap<>();
    }

    public Ticket park(Car car) {
        if (hasAvailableSpace()) {
            Ticket ticket = new Ticket();
            parkedCars.put(ticket, car);

            return ticket;
        } else {
            throw new NoSpaceAvailableException();
        }
    }

    public Car pick(Ticket ticket) {
        if (isCarParkedIn(ticket)) {
            return parkedCars.remove(ticket);
        } else {
            throw new CarNotMatchException();
        }
    }

    private boolean hasAvailableSpace() {
        return totalSpace - parkedCars.size() > 0;
    }

    private boolean isCarParkedIn(Ticket ticket) {
        return parkedCars.containsKey(ticket);
    }
}
