package cc.oobootcamp.parkinglot;

import cc.oobootcamp.parkinglot.exception.CarNotMatchException;
import cc.oobootcamp.parkinglot.exception.NoSpaceAvailableException;
import cc.oobootcamp.parkinglot.model.Car;
import cc.oobootcamp.parkinglot.model.Ticket;
import cc.oobootcamp.parkinglot.service.GraduateParkingBoy;
import cc.oobootcamp.parkinglot.service.ParkingBoy;
import cc.oobootcamp.parkinglot.service.ParkingLot;
import cc.oobootcamp.parkinglot.service.ParkingManager;
import cc.oobootcamp.parkinglot.service.SmartParkingBoy;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotManagerTest {
    @Test
    void should_return_ticket_when_park_given_a_parking_manager_maneged_a_smart_parking_boy_and_a_parking_lot_both_has_available_space() {
        ParkingBoy smartParkingBoy = new SmartParkingBoy(singletonList(new ParkingLot(2)));
        ParkingLot managerParkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(singletonList(smartParkingBoy), singletonList(managerParkingLot));
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        assertNotNull(ticket);
        assertThat(smartParkingBoy.pick(ticket)).isEqualTo(car);
        assertThrows(CarNotMatchException.class, () -> managerParkingLot.pick(ticket));
    }

    @Test
    void should_return_ticket_when_park_given_a_parking_manager_maneged_a_graduate_parking_boy_and_a_parking_lot_both_has_available_space() {
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(singletonList(new ParkingLot(2)));
        ParkingLot managerParkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(singletonList(graduateParkingBoy), singletonList(managerParkingLot));
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        assertNotNull(ticket);
        assertThat(graduateParkingBoy.pick(ticket)).isEqualTo(car);
        assertThrows(CarNotMatchException.class, () -> managerParkingLot.pick(ticket));
    }

    @Test
    void should_return_ticket_when_park_given_a_parking_manager_maneged_a_graduate_parking_boy_and_a_smart_parking_boy_and_a_parking_lot_all_have_available_space() {
        ParkingBoy graduateParkingBoy = new GraduateParkingBoy(singletonList(new ParkingLot(2)));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(singletonList(new ParkingLot(2)));
        ParkingLot managerParkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(asList(graduateParkingBoy, smartParkingBoy), singletonList(managerParkingLot));
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        assertNotNull(ticket);
        assertThat(graduateParkingBoy.pick(ticket)).isEqualTo(car);
        assertThrows(CarNotMatchException.class, () -> managerParkingLot.pick(ticket));
    }

    @Test
    void should_return_ticket_when_park_given_a_parking_manager_maneged_a_parking_lot_with_space_and_a_parking_boy_whose_parking_lot_is_full() {
        ParkingBoy parkingBoy = new SmartParkingBoy(singletonList(new ParkingLot(1)));
        parkingBoy.park(new Car());
        ParkingLot managerParkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(singletonList(parkingBoy), singletonList(managerParkingLot));
        Car car = new Car();

        Ticket ticket = parkingManager.park(car);

        assertNotNull(ticket);
        assertThat(managerParkingLot.pick(ticket)).isEqualTo(car);
        assertThrows(CarNotMatchException.class, () -> parkingBoy.pick(ticket));
    }

    @Test
    void should_return_error_when_park_given_a_parking_manager_maneged_a_parking_lot_and_a_parking_boy_all_are_full() {
        ParkingBoy parkingBoy = new SmartParkingBoy(singletonList(new ParkingLot(1)));
        parkingBoy.park(new Car());
        ParkingLot managerParkingLot = new ParkingLot(1);
        managerParkingLot.park(new Car());
        ParkingManager parkingManager = new ParkingManager(singletonList(parkingBoy), singletonList(managerParkingLot));

        assertThrows(NoSpaceAvailableException.class, () -> parkingManager.park(new Car()));
    }

    @Test
    void should_give_the_car_when_park_given_a_parking_manager_maneged_a_parking_lot_and_a_parking_boy_and_a_valid_ticket() {
        ParkingBoy parkingBoy = new SmartParkingBoy(singletonList(new ParkingLot(2)));
        ParkingLot managerParkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(singletonList(parkingBoy), singletonList(managerParkingLot));

        Car parkedByManager = new Car();
        Ticket ticketFromManager = managerParkingLot.park(parkedByManager);

        Car parkedByParkingBoy = new Car();
        Ticket ticketFromParkingBoy = parkingBoy.park(parkedByParkingBoy);


        Car pickedByManager = parkingManager.pick(ticketFromManager);
        Car pickedByParkingBoy = parkingManager.pick(ticketFromParkingBoy);

        assertThat(pickedByManager).isEqualTo(parkedByManager);
        assertThat(pickedByParkingBoy).isEqualTo(parkedByParkingBoy);
    }

    @Test
    void should_return_error_when_park_given_a_parking_manager_maneged_a_parking_lot_and_a_parking_boy_and_a_invalid_ticket() {
        ParkingBoy parkingBoy = new SmartParkingBoy(singletonList(new ParkingLot(2)));
        ParkingLot managerParkingLot = new ParkingLot(2);
        ParkingManager parkingManager = new ParkingManager(singletonList(parkingBoy), singletonList(managerParkingLot));

        assertThrows(CarNotMatchException.class, () -> parkingManager.pick(new Ticket()));
    }
}
