package cc.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_two_parking_lots_both_has_available_spaces_managed_by_parking_boy() {
        ParkingLot lessSpaceParkingLot = new ParkingLot(2);
        ParkingLot moreSpaceParkingLot = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(lessSpaceParkingLot, moreSpaceParkingLot));
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertSame(moreSpaceParkingLot.pick(ticket), car);
        assertThrows(CarNotMatchException.class, () -> lessSpaceParkingLot.pick(ticket));
    }

    @Test
    void should_return_ticket_when_park_given_two_parking_lots_one_has_available_spaces_managed_by_parking_boy() {
        ParkingLot firstParkingLot = buildFullParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car car = new Car();

        Ticket ticket = smartParkingBoy.park(car);

        assertNotNull(ticket);
        assertThrows(CarNotMatchException.class, () -> firstParkingLot.pick(ticket));
        assertSame(secondParkingLot.pick(ticket), car);
    }

    @Test
    void should_return_error_when_park_given_two_full_parking_lots_managed_by_parking_boy() {
        ParkingLot firstParkingLot = buildFullParkingLot();
        ParkingLot secondParkingLot = buildFullParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car car = new Car();

        assertThrows(NoSpaceAvailableException.class, () -> smartParkingBoy.park(car));
    }

    @Test
    void should_return_car_when_pick_given_two_parking_lots_managed_by_parking_boy_parked_corresponding_car() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car myCar = new Car();
        Ticket myTicket = smartParkingBoy.park(myCar);

        Car pickedCar = smartParkingBoy.pick(myTicket);

        assertSame(pickedCar, myCar);
    }

    @Test
    void should_return_error_when_pick_given_two_parking_lots_managed_by_parking_boy_without_corresponding_car() {
        ParkingLot fistParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(fistParkingLot, secondParkingLot));

        assertThrows(CarNotMatchException.class, () -> smartParkingBoy.pick(new Ticket()));
    }

    private ParkingLot buildFullParkingLot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        firstParkingLot.park(new Car());
        return firstParkingLot;
    }
}
