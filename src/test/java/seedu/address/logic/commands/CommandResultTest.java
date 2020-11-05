package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.consumption.ListConsumptionCommand;
import seedu.address.logic.commands.ingredient.GetEditIngredientCommand;
import seedu.address.logic.commands.ingredient.ListIngredientsCommand;
import seedu.address.logic.commands.recipe.GetEditRecipeCommand;
import seedu.address.logic.commands.recipe.ListRecipesCommand;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback")));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", "help")));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", "exit")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", "help").hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", "exit").hashCode());

        // different recipe value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                ListRecipesCommand.COMMAND_WORD).hashCode());

        // different ingredient value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                ListIngredientsCommand.COMMAND_WORD).hashCode());

        // different consumption value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                ListConsumptionCommand.COMMAND_WORD).hashCode());

        // different isEditRecipe value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                GetEditRecipeCommand.COMMAND_WORD).hashCode());

        // different isEditIngredient value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback",
                GetEditIngredientCommand.COMMAND_WORD).hashCode());

    }

    @Test
    public void getAttributesOnInitialization() {
        CommandResult commandResult = new CommandResult("feedback");

        assertFalse(commandResult.isShowHelp());

        assertFalse(commandResult.isExit());

        assertFalse(commandResult.isShowRecipe());

        assertFalse(commandResult.isShowIngredient());

        assertFalse(commandResult.isShowConsumption());

        assertFalse(commandResult.isEditRecipe());

        assertFalse(commandResult.isEditIngredient());

        assertFalse(commandResult.isClose());
    }
}
