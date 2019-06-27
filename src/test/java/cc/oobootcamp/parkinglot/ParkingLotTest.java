package cc.oobootcamp.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotE2ETest {

    @Test
    void should_return_valid_parking_lot_when_init_given_space_in_use_and_space_available() {
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
}
