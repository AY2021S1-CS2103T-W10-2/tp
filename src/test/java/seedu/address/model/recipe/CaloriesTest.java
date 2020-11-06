package seedu.address.model.recipe;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CaloriesTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Calories(null));
    }
}
