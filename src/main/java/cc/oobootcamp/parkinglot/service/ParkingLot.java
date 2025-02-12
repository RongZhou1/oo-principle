package cc.oobootcamp.parkinglot.service;

import cc.oobootcamp.parkinglot.exception.CarNotMatchException;
import cc.oobootcamp.parkinglot.exception.NoSpaceAvailableException;
import cc.oobootcamp.parkinglot.model.Car;
import cc.oobootcamp.parkinglot.model.Ticket;

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

    int getAvailableSpace() {
        return totalSpace - parkedCars.size();
    }

    boolean isCarParkedIn(Ticket ticket) {
        return parkedCars.containsKey(ticket);
    }

    private boolean hasAvailableSpace() {
        return totalSpace - parkedCars.size() > 0;
    }
}
