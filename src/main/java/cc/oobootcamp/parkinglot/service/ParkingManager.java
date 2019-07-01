package cc.oobootcamp.parkinglot.service;

import cc.oobootcamp.parkinglot.exception.CarNotMatchException;
import cc.oobootcamp.parkinglot.exception.NoSpaceAvailableException;
import cc.oobootcamp.parkinglot.model.Car;
import cc.oobootcamp.parkinglot.model.Ticket;

import java.util.List;

public class ParkingManager {
    private List<ParkingBoy> parkingBoys;
    private List<ParkingLot> parkingLots;

    public ParkingManager(List<ParkingBoy> parkingBoys, List<ParkingLot> parkingLots) {
        this.parkingBoys = parkingBoys;
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        if (isParkingBoyAvailable()) {
            return parkByParkingBoy(car);
        } else {
            return parkByMySelf(car);
        }

    }

    public Car pick(Ticket ticket) {
        if (isParkedByParkingBoy(ticket)) {
            return pickByParkingBoy(ticket);
        } else {
            return pickByMySelf(ticket);
        }

    }

    private Car pickByMySelf(Ticket ticket) {
        return parkingLots.stream()
                .filter(i -> i.isCarParkedIn(ticket))
                .findFirst()
                .map(i -> i.pick(ticket))
                .orElseThrow(CarNotMatchException::new);
    }

    private boolean isParkedByParkingBoy(Ticket ticket) {
        return parkingBoys.stream().anyMatch(i -> i.isCarParkedByMe(ticket));
    }

    private Car pickByParkingBoy(Ticket ticket) {
        return parkingBoys.stream()
                .filter(i -> i.isCarParkedByMe(ticket))
                .findFirst()
                .map(i -> i.pick(ticket))
                .orElse(null);
    }

    private boolean isParkingBoyAvailable() {
        return parkingBoys.stream().anyMatch(ParkingBoy::hasAvailableSpace);
    }

    private Ticket parkByParkingBoy(Car car) {
        return parkingBoys.stream()
                .filter(ParkingBoy::hasAvailableSpace)
                .findFirst()
                .map(i -> i.park(car))
                .orElse(null);
    }

    private Ticket parkByMySelf(Car car) {
        return parkingLots.stream()
                .filter(i -> i.getAvailableSpace() > 0)
                .findFirst()
                .map(i -> i.park(car))
                .orElseThrow(NoSpaceAvailableException::new);
    }
}
