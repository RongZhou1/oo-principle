package cc.oobootcamp.parkinglot;

public class ParkingLot {

    private int spaceInUse, spaceAvailable;

    public ParkingLot(int spaceInUse, int spaceAvailable) {
        this.spaceInUse = spaceInUse;
        this.spaceAvailable = spaceAvailable;
    }

    public Ticket park(Car car) {
        return new Ticket();
    }

    public int getSpaceInUse() {
        return spaceInUse;
    }

    public int getSpaceAvailable() {
        return spaceAvailable;
    }
}
