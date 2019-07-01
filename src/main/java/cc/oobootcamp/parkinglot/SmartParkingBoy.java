package cc.oobootcamp.parkinglot;


import java.util.List;

import static java.util.Comparator.comparing;

public class SmartParkingBoy extends ParkingBoy{

    protected SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .max(comparing(ParkingLot::getAvailableSpace))
                .map(parkingLot -> parkingLot.park(car))
                .orElseThrow(NoSpaceAvailableException::new);
    }
}
