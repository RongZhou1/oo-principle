package cc.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_two_parking_lots_both_has_available_spaces_managed_by_parking_boy() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertSame(firstParkingLot.pick(ticket), car);
        assertThrows(CarNotMatchException.class, () -> secondParkingLot.pick(ticket));
    }

    @Test
    void should_return_ticket_when_park_given_two_parking_lots_one_has_available_spaces_managed_by_parking_boy() {
        ParkingLot firstParkingLot = buildFullParkingLot();
        ParkingLot secondParkingLot = new ParkingLot(2);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car car = new Car();

        Ticket ticket = graduateParkingBoy.park(car);

        assertNotNull(ticket);
        assertThrows(CarNotMatchException.class, () -> firstParkingLot.pick(ticket));
        assertSame(secondParkingLot.pick(ticket), car);
    }

    @Test
    void should_return_error_when_park_given_two_full_parking_lots_managed_by_parking_boy() {
        ParkingLot firstParkingLot = buildFullParkingLot();
        ParkingLot secondParkingLot = buildFullParkingLot();
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car car = new Car();

        assertThrows(NoSpaceAvailableException.class, () -> graduateParkingBoy.park(car));
    }

    @Test
    void should_return_car_when_pick_given_two_parking_lots_managed_by_parking_boy_parked_corresponding_car() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(3);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(Arrays.asList(firstParkingLot, secondParkingLot));
        Car myCar = new Car();
        Ticket myTicket = graduateParkingBoy.park(myCar);

        Car pickedCar = graduateParkingBoy.pick(myTicket);

        assertSame(pickedCar, myCar);
    }

    @Test
    void should_return_error_when_pick_given_two_parking_lots_managed_by_parking_boy_without_corresponding_car() {
        ParkingLot fistParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot =new ParkingLot(3);
        GraduateParkingBoy graduateParkingBoy =  new GraduateParkingBoy(Arrays.asList(fistParkingLot,secondParkingLot));

        assertThrows(CarNotMatchException.class,()->graduateParkingBoy.pick(new Ticket()));
    }

    private ParkingLot buildFullParkingLot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        firstParkingLot.park(new Car());
        return firstParkingLot;
    }
}
