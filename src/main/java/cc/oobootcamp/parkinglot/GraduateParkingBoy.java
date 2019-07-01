package cc.oobootcamp.parkinglot;

import java.util.List;

public class GraduateParkingBoy extends ParkingBoy {


    protected GraduateParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .filter(this::hasAvailableSpace)
                .findFirst()
                .map(parkingLot -> parkingLot.park(car))
                .orElseThrow(NoSpaceAvailableException::new);
    }

    private boolean hasAvailableSpace(ParkingLot parkingLot) {
        return parkingLot.getAvailableSpace() > 0;
    }
}
