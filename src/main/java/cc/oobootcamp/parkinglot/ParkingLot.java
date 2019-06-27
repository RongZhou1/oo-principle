package cc.oobootcamp.parkinglot;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;
import static java.util.stream.IntStream.range;

class ParkingLot {
    private static final String TICKET_INVALID = "Invalid ticket.";
    private static final String PICK_UP_SUCCEED = "Successfully.";
    private static final String PARKING_FAILED_MESSAGE = "No enough space.";
    private static final String PARKING_SUCCEED_MESSAGE = "Parking successfully, pick up your ticket.";

    private int spaceInUse;
    private int spaceAvailable;
    private List<Ticket> tickets;
    private String verificationCode;

    public ParkingLot(int spaceInUse, int spaceAvailable) {
        this.spaceInUse = spaceInUse;
        this.spaceAvailable = spaceAvailable;
        this.tickets = new ArrayList<>();
        range(0, spaceInUse)
                .forEach(i -> this.tickets.add(generateTicket()));
        this.verificationCode = randomUUID().toString();
    }

    private Ticket generateTicket() {
        return new Ticket(verificationCode);
    }

    public String park(Car car) {
        return hasAvailableCarSpace() ? parkSucceed() : parkFailed();
    }

    private boolean hasAvailableCarSpace() {
        return this.spaceAvailable > 0;
    }

    private String parkSucceed() {
        this.spaceInUse++;
        this.spaceAvailable--;
        tickets.add(generateTicket());
        return PARKING_SUCCEED_MESSAGE;
    }

    private String parkFailed() {
        return PARKING_FAILED_MESSAGE;
    }

    public String pickUpCar(Ticket ticket) {
        return isValidTicket(ticket) ? pickUpSucceed() : pickUpFailed();
    }

    private boolean isValidTicket(Ticket ticket) {
        return this.verificationCode.equals(ticket.getValidationCode());
    }

    private String pickUpSucceed() {
        this.spaceInUse--;
        this.spaceAvailable++;
        this.tickets.remove(0);
        return PICK_UP_SUCCEED;
    }

    private String pickUpFailed() {
        return TICKET_INVALID;
    }

    public int getSpaceInUse() {
        return spaceInUse;
    }

    public int getSpaceAvailable() {
        return spaceAvailable;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}
