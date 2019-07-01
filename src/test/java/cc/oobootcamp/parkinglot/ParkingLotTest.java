package cc.oobootcamp.parkinglot;

import cc.oobootcamp.parkinglot.exception.CarNotMatchException;
import cc.oobootcamp.parkinglot.exception.NoSpaceAvailableException;
import cc.oobootcamp.parkinglot.model.Car;
import cc.oobootcamp.parkinglot.model.Ticket;
import cc.oobootcamp.parkinglot.service.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot_with_available_space() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2);

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }

    @Test
    void should_return_error_when_park_given_a_car_and_a_parking_lot_without_available_space() {
        Car car = new Car();
        ParkingLot parkingLot = buildFullParkingLot();

        assertThrows(NoSpaceAvailableException.class, () -> parkingLot.park(car));
    }

    @Test
    void should_give_the_car_when_pick_given_a_ticket_and_a_parking_lot_parked_corresponding_car() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car myCar = new Car();
        Ticket ticket = parkingLot.park(myCar);

        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(pickedCar, myCar);
    }

    @Test
    void should_return_error_when_pick_given_a_ticket_and_a_parking_lot_without_corresponding_car() {
        ParkingLot parkingLot = new ParkingLot(2);
        Ticket ticket = new Ticket();

        assertThrows(CarNotMatchException.class, () -> parkingLot.pick(ticket));
    }

    @Test
    void should_return_error_when_pick_twice_given_a_ticket_and_a_parking_lot_with_corresponding_car() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.pick(ticket);

        assertThrows(CarNotMatchException.class, () -> parkingLot.pick(ticket));

    }

    private ParkingLot buildFullParkingLot() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());
        return parkingLot;
    }
}
