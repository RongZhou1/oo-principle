package cc.oobootcamp.parkinglot;

class Ticket {
    private String validationCode;

    public Ticket(String validationCode) {
        this.validationCode = validationCode;
    }

    String getValidationCode() {
        return validationCode;
    }
}
