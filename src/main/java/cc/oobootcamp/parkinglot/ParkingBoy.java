package cc.oobootcamp.parkinglot;

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
}
