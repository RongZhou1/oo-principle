package cc.oobootcamp.length;

public class Length {
    private int value;

    public Length(int value) {
        this.value = value;
    }

    public boolean isLongerThan(Length length) {
        return this.value > length.value;
    }

    public boolean isShorterThan(Length length) {
        return this.value < length.value;
    }

    public boolean isEqualTo(Length length) {
        return this.value == length.value;
    }
}
