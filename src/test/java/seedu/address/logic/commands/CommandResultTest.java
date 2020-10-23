package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback",
                CommandResult.ResultType.NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                CommandResult.ResultType.SHOW_HELP)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback",
                CommandResult.ResultType.EXIT)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.SHOW_HELP).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.EXIT).hashCode());

        // different recipe value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.IS_SHOW_RECIPE).hashCode());

        // different ingredient value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.IS_SHOW_INGREDIENT).hashCode());

        // different consumption value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.IS_SHOW_CONSUMPTION).hashCode());

        // different isEditRecipe value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.IS_EDIT_RECIPE).hashCode());

        // different isEditIngredient value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                CommandResult.ResultType.IS_EDIT_INGREDIENT).hashCode());

    }
}
