package seedu.address.model.consumption;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.testutil.TypicalRecipes.MARGARITAS;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ConsumptionBuilder;
import seedu.address.testutil.RecipeBuilder;

public class ConsumptionTest {

    private final Consumption alice = new Consumption(SANDWICH);

    @Test
    public void equals() {
        // same values -> returns true
        Consumption aliceCopy = new ConsumptionBuilder(alice).build();
        assertTrue(alice.equals(aliceCopy));

        // same object -> returns true
        assertTrue(alice.equals(new Consumption(SANDWICH)));

        // null -> returns false
        assertFalse(alice.equals(null));

        // different type -> returns false
        assertFalse(alice.equals(5));

        // different recipe -> returns false
        assertFalse(alice.equals(MARGARITAS));

        // different name -> returns false
        Consumption editedAlice = new Consumption(new RecipeBuilder(SANDWICH).withName(VALID_NAME_MARGARITAS).build());
        assertFalse(alice.equals(editedAlice));

        // different ingredients -> returns false
        editedAlice =
                new Consumption(new RecipeBuilder(SANDWICH)
                        .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                        .build());
        assertFalse(alice.equals(editedAlice));

    }
}
