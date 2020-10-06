package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringUtil;

public class Calories {
    public static final String MESSAGE_CONSTRAINTS =
            "Calories should only a positive Integer number, and it should not be blank";
    private static final int MIN_CALORIES = 0;
    public final Integer value;

    /**
     * Constructs a {@code Calories}.
     *
     * @param calories A valid ingredients number.
     */
    public Calories(Integer calories) {
        requireNonNull(calories);
        value = calories;
    }

    public int getValue() {
        return value;
    }

    /**
     * Returns true if a given string is a valid Calorie.
     */
    public static boolean isValidCalories(String test) {
        return StringUtil.isNonZeroUnsignedInteger(test);
    }

    public static boolean isValidCalories(int test) {
        return test >= MIN_CALORIES;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Calories // instanceof handles nulls
                && value.equals(((Calories) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
