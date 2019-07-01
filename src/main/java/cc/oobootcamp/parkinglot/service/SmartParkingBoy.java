package cc.oobootcamp.parkinglot.service;


import cc.oobootcamp.parkinglot.exception.NoSpaceAvailableException;
import cc.oobootcamp.parkinglot.model.Car;
import cc.oobootcamp.parkinglot.model.Ticket;

import java.util.List;

import static java.util.Comparator.comparing;

public class SmartParkingBoy extends ParkingBoy{

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
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
