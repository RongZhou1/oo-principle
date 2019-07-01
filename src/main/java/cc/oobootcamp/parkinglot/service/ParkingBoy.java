package cc.oobootcamp.parkinglot.service;

import cc.oobootcamp.parkinglot.exception.CarNotMatchException;
import cc.oobootcamp.parkinglot.model.Car;
import cc.oobootcamp.parkinglot.model.Ticket;

import java.util.List;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    protected ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract Ticket park(Car car);

    public Car pick(Ticket ticket) {
        return parkingLots
                .stream()
                .filter(i -> i.isCarParkedIn(ticket))
                .findFirst()
                .map(parkingLot -> parkingLot.pick(ticket))
                .orElseThrow(CarNotMatchException::new);
    }

    boolean hasAvailableSpace() {
        return parkingLots.stream().anyMatch(i -> i.getAvailableSpace() > 0);
    }

    boolean isCarParkedByMe(Ticket ticket) {
        return parkingLots.stream().anyMatch(i -> i.isCarParkedIn(ticket));
    }
}
