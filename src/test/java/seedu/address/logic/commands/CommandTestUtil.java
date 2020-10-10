package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.consumption.ConsumptionContainsKeywordsPredicate;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.KeywordsContainIngredientPredicate;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_INGREDIENT_AMY = "11111111";
    public static final String VALID_INGREDIENT_BOB = "22222222";
    public static final Integer VALID_CALORIES_AMY = 10;
    public static final Integer VALID_CALORIES_BOB = 10;
    public static final String VALID_INSTRUCTION_AMY = "instruction";
    public static final String VALID_INSTRUCTION_BOB = "instruction2";
    public static final String VALID_RECIPE_IMAGE_AMY = "images/healthy1";
    public static final String VALID_RECIPE_IMAGE_BOB = "images/healthy2";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String INGREDIENT_DESC_AMY = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_AMY;
    public static final String INGREDIENT_DESC_BOB = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_BOB;
    public static final String CALORIES_DESC_AMY = " " + PREFIX_CALORIES + VALID_CALORIES_AMY;
    public static final String CALORIES_DESC_BOB = " " + PREFIX_CALORIES + VALID_CALORIES_BOB;
    public static final String INSTRUCTION_DESC_AMY = " " + PREFIX_INSTRUCTION + VALID_INSTRUCTION_AMY;
    public static final String INSTRUCTION_DESC_BOB = " " + PREFIX_INSTRUCTION + VALID_INSTRUCTION_BOB;
    public static final String RECIPE_IMAGE_DESC_AMY = " " + PREFIX_RECIPE_IMAGE + VALID_RECIPE_IMAGE_AMY;
    public static final String RECIPE_IMAGE_DESC_BOB = " " + PREFIX_RECIPE_IMAGE + VALID_RECIPE_IMAGE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " "
            + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_INGREDIENT_DESC = " "
            + PREFIX_INGREDIENT + " "; // not allowed in to blank the ingredients
    public static final String INVALID_CALORIES_DESC = " " + PREFIX_CALORIES + "-1"; // negative number
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditRecipeDescriptor DESC_AMY;
    public static final EditCommand.EditRecipeDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditRecipeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withIngredient(VALID_INGREDIENT_AMY).withCalories(VALID_CALORIES_AMY).build();
        DESC_BOB = new EditRecipeDescriptorBuilder().withName(VALID_NAME_BOB)
                .withIngredient(VALID_INGREDIENT_BOB).withCalories(VALID_CALORIES_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            System.out.println(result.getFeedbackToUser());
            System.out.println(expectedCommandResult.getFeedbackToUser());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the Wishful Shrinking, filtered recipe list and selected recipe in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        WishfulShrinking expectedWishfulShrinking = new WishfulShrinking(actualModel.getWishfulShrinking());
        List<Recipe> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRecipeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedWishfulShrinking, actualModel.getWishfulShrinking());
        assertEquals(expectedFilteredList, actualModel.getFilteredRecipeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the recipe at the given {@code targetIndex} in the
     * {@code model}'s recipe collection.
     */
    public static void showRecipeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecipeList().size());

        Recipe recipe = model.getFilteredRecipeList().get(targetIndex.getZeroBased());
        final String[] splitName = recipe.getName().fullName.split("\\s+");
        model.updateFilteredRecipeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRecipeList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the ingredient at the given {@code targetIndex} in the
     * {@code model}'s fridge.
     */
    public static void showIngredientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredIngredientList().size());

        Ingredient ingredient = model.getFilteredIngredientList().get(targetIndex.getZeroBased());
        final String[] splitName = ingredient.getValue().split("\\s+");
        model.updateFilteredIngredientList(new KeywordsContainIngredientPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredIngredientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the consumption at the given {@code targetIndex} in the
     * {@code model}'s consumption list.
     */
    public static void showConsumptionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredConsumptionList().size());

        Consumption consumption = model.getFilteredConsumptionList().get(targetIndex.getZeroBased());
        final String[] splitName = consumption.getRecipe().getName().fullName.split("\\s+");
        model.updateFilteredConsumptionList(new ConsumptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredConsumptionList().size());
    }
}
