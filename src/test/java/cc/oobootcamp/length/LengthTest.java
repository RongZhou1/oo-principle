package cc.oobootcamp.length;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LengthTest {

    @Test
    void should_return_true_when_compare_longer_given_first_length_longer_than_second() {
        Length longerLength = new Length(6);
        Length shorterLength = new Length(1);

        assertTrue(longerLength.isLongerThan(shorterLength));
    }

    @Test
    void should_return_false_when_compare_longer_given_first_length_equals_to_second() {
        Length firstLength = new Length(6);
        Length secondLength = new Length(6);

        assertFalse(firstLength.isLongerThan(secondLength));
    }

    @Test
    void should_return_false_when_compare_longer_given_first_length_shorter_than_second() {
        Length firstLength = new Length(1);
        Length secondLength = new Length(6);

        assertFalse(firstLength.isLongerThan(secondLength));
    }
}