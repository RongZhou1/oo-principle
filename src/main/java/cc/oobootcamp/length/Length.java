package cc.oobootcamp.length;

public class Length {
    private int value;

    public Length(int value) {
        this.value = value;
    }

    public boolean isLongerThan(Length length) {
        boolean result = false;
        if (this.value > length.value) {
            result = true;
        }
        return result;
    }

    public boolean isShorterThan(Length length) {
        boolean result = false;
        if (this.value < length.value) {
            result = true;
        }
        return result;
    }
}
