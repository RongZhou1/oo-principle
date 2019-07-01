package cc.oobootcamp.parkinglot;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .filter(ParkingLot::hasAvailableSpace)
                .findFirst()
                .map(parkingLot -> parkingLot.park(car))
                .orElseThrow(NoSpaceAvailableException::new);
    }

    public Car pick(Ticket ticket) {
        return parkingLots
                .stream()
                .filter(i -> i.isCarParkedIn(ticket))
                .findFirst()
                .map(parkingLot -> parkingLot.pick(ticket))
                .orElseThrow(CarNotMatchException::new);
    }
}
