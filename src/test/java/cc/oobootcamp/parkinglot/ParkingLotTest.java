package cc.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotTest {

    @Test
    void should_return_parking_lot_with_related_ticket_number_when_init_given_space_in_use_and_space_available() {
        ParkingLot parkingLot = new ParkingLot(2, 6);

        assertEquals(2, parkingLot.getTickets().size());
    }

    @Test
    void should_return_successful_message_and_update_parking_lot_status_when_car_parking_into_given_parking_lot_with_available_space() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(0, 1);

        String message = parkingLot.park(car);

        assertEquals("Parking successfully, pick up your ticket.", message);
        assertEquals(1, parkingLot.getSpaceInUse());
        assertEquals(0, parkingLot.getSpaceAvailable());
        assertEquals(1, parkingLot.getTickets().size());
    }

    @Test
    void should_return_error_message_and_not_update_parking_lot_status_when_car_parking_into_given_parking_lot_without_available_space() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(2, 0);

        String message = parkingLot.park(car);

        assertEquals("No enough space.", message);
        assertEquals(2, parkingLot.getTickets().size());
        assertEquals(2, parkingLot.getSpaceInUse());
        assertEquals(0, parkingLot.getSpaceAvailable());
    }

    @Test
    void should_return_successful_message_and_update_parking_lot_status_when_pick_up_car_given_valid_ticket() {
        ParkingLot parkingLot = new ParkingLot(3, 0);
        Ticket ticket = new Ticket(parkingLot.getVerificationCode());

        String message = parkingLot.pickUpCar(ticket);

        assertEquals("Successfully.", message);
        assertEquals(2, parkingLot.getSpaceInUse());
        assertEquals(1, parkingLot.getSpaceAvailable());
        assertEquals(2, parkingLot.getTickets().size());
    }

    @Test
    void should_return_error_message_and_not_update_parking_lot_status_when_pick_up_car_given_invalid_ticket() {
        ParkingLot parkingLot = new ParkingLot(3, 1);
        Ticket ticket = new Ticket("oooooo");

        String message = parkingLot.pickUpCar(ticket);

        assertEquals("Invalid ticket.", message);
        assertEquals(3, parkingLot.getSpaceInUse());
        assertEquals(1, parkingLot.getSpaceAvailable());
        assertEquals(3, parkingLot.getTickets().size());
    }
}
