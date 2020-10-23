package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.GetEditIngredientCommand.MESSAGE_GET_EDIT_INGREDIENT_SUCCESS;
import static seedu.address.logic.commands.GetEditRecipeCommand.MESSAGE_GET_EDIT_RECIPE_SUCCESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;

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
import seedu.address.model.recipe.IngredientContainsKeywordsPredicate;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public enum Field {
        INGREDIENT, RECIPE_NAME, CALORIES, TAG, INDEX, INSTRUCTIONS, RECIPE_IMAGE,
        INGREDIENT_NAME, INGREDIENT_QUANTITY
    }

    public enum Number {
        NEGATIVE, NON_INTEGER, ZERO
    }

    public static final EditIngredientCommand.EditIngredientDescriptor DESC_INGREDIENT_MARGARITAS;
    public static final EditIngredientCommand.EditIngredientDescriptor DESC_INGREDIENT_NOODLE;

    public static final EditRecipeCommand.EditRecipeDescriptor DESC_MARGARITAS;
    public static final EditRecipeCommand.EditRecipeDescriptor DESC_NOODLE;

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";

    //SANDWICH
    public static final String VALID_INGREDIENT_SANDWICH = "Kaiser Rolls Or Other Bread";
    public static final String VALID_QUANTITY_SANDWICH = "2 whole";

    // NOODLE
    public static final String VALID_NAME_NOODLE = "Buttery Lemon Parsley Noodles";
    public static final String VALID_INGREDIENT_NOODLE = "Pasta fettuccine";
    public static final String VALID_QUANTITY_NOODLE = "1/2 pound";
    public static final Integer VALID_CALORIES_NOODLE = 180;
    public static final String VALID_INSTRUCTION_NOODLE = "Cook the noodles according to package instructions. "
            + "(If using angel hair, stop just short of the al dente stage.) Drain and set aside. "
            + "Melt butter in a large skillet over medium-high heat. "
            + "Throw in the cooked pasta and cook it around in the butter for a couple of minutes "
                + "so that a few of the noodles get a little bit of a panfried texture to them, whatever that means. "
            + "Zest the lemon. "
            + "Squeeze in the juice, then add the zest of half the lemon, salt and pepper to taste,"
                + " then toss around and serve.";
    public static final String VALID_RECIPE_IMAGE_NOODLE = "images/noodles1.jpg";
    public static final String VALID_TAG_NOODLE = "healthy";

    public static final String NAME_DESC_NOODLE = " " + PREFIX_NAME + VALID_NAME_NOODLE;
    public static final String INGREDIENT_DESC_NOODLE = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_NOODLE
            + " " + PREFIX_QUANTITY + VALID_QUANTITY_NOODLE;
    public static final String CALORIES_DESC_NOODLE = " " + PREFIX_CALORIES + VALID_CALORIES_NOODLE;
    public static final String INSTRUCTION_DESC_NOODLE = " " + PREFIX_INSTRUCTION + VALID_INSTRUCTION_NOODLE;
    public static final String RECIPE_IMAGE_DESC_NOODLE = " " + PREFIX_RECIPE_IMAGE + VALID_RECIPE_IMAGE_NOODLE;
    public static final String TAG_DESC_NOODLE = " " + PREFIX_TAG + VALID_TAG_NOODLE;
    public static final String DESC_NOODLE_USER_INPUT = NAME_DESC_NOODLE + INGREDIENT_DESC_NOODLE + CALORIES_DESC_NOODLE
            + INSTRUCTION_DESC_NOODLE + RECIPE_IMAGE_DESC_NOODLE + TAG_DESC_NOODLE;


    // MARGARITAS
    public static final String VALID_NAME_MARGARITAS = "Mango Margaritas";
    public static final String VALID_INGREDIENT_MARGARITAS = "Mango Chunks Drained";
    public static final String VALID_QUANTITY_MARGARITAS = "2.5 jars";
    public static final Integer VALID_CALORIES_MARGARITAS = 80;
    public static final String VALID_INSTRUCTION_MARGARITAS = "Zest the limes and lay the zest on a plate. "
            + "If you have the time, let the zest dry out for ten minutes or so. "
            + "Pour the coarse sugar over the zest and toss it around with your fingers to combine. "
            + "Lime sugar! "
            + "Yum. Throw the mango chunks into a blender. "
            + "Pour in the tequila, triple sec, and sugar. "
            + "Squeeze in the juice of the limes, then top off the whole thing with ice. "
            + "Blend it until it's totally smooth. "
            + "Give it a taste, then add more of what you think it needs (alcohol, sugar, lime, etc.) "
            + "To serve, cut a small wedge in one of the juiced limes and "
            + "rub the lime all over the rim of the glass to moisten. "
            + "Dip the rim of the glasses in the lime sugar to give it a pretty, crystally rim. "
            + "Pour in the margaritas and serve immediately!";
    public static final String VALID_RECIPE_IMAGE_MARGARITAS = "images/mango23.jpg";
    public static final String VALID_TAG_MARGARITAS = "healthy";

    public static final String NAME_DESC_MARGARITAS = " " + PREFIX_NAME + VALID_NAME_MARGARITAS;
    public static final String INGREDIENT_DESC_MARGARITAS = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_MARGARITAS
            + " " + PREFIX_QUANTITY + VALID_QUANTITY_MARGARITAS;
    public static final String CALORIES_DESC_MARGARITAS = " " + PREFIX_CALORIES + VALID_CALORIES_MARGARITAS;
    public static final String INSTRUCTION_DESC_MARGARITAS = " " + PREFIX_INSTRUCTION + VALID_INSTRUCTION_MARGARITAS;
    public static final String RECIPE_IMAGE_DESC_MARGARITAS = " " + PREFIX_RECIPE_IMAGE + VALID_RECIPE_IMAGE_MARGARITAS;
    public static final String TAG_DESC_MARGARITAS = " " + PREFIX_TAG + VALID_TAG_MARGARITAS;
    public static final String DESC_MARGARITAS_USER_INPUT =
            NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS + INSTRUCTION_DESC_MARGARITAS
            + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;

    //Invalid Recipe Name with prefix
    public static final String INVALID_NAME_DESC = " "
            + PREFIX_NAME + "Butter & Chicken!"; // only alphanumeric characters allowed
    public static final String MISSING_NAME_DESC = " " + PREFIX_NAME;

    //Invalid Ingredient with prefix
    public static final String INVALID_INGREDIENT_DESC = " " + PREFIX_INGREDIENT
            + "Chicken!"; // only alphanumeric characters allowed
    public static final String INVALID_INGREDIENT_QUANTITY = " " + PREFIX_QUANTITY
            + "2@3a"; // only alphanumeric, full stop, forward slash allowed
    public static final String MISSING_INGREDIENT_DESC = " " + PREFIX_INGREDIENT;

    //Invalid Calories with prefix
    public static final String NEGATIVE_CALORIES_DESC = " " + PREFIX_CALORIES
            + "-1"; // only positive integers allowed
    public static final String NON_INTEGER_CALORIES_DESC = " " + PREFIX_CALORIES
            + "1.1"; // only positive numbers allowed
    public static final String ZERO_CALORIES_DESC = " " + PREFIX_CALORIES
            + "0"; // only positive numbers allowed
    public static final String MISSING_CALORIES_DESC = " " + PREFIX_CALORIES;

    //Invalid Tags with prefix
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG
            + "healthy*"; // only alphanumeric characters allowed
    public static final String MISSING_TAG_DESC = " " + PREFIX_TAG;

    //Invalid Instructions
    public static final String MISSING_INSTRUCTIONS_DESC = " " + PREFIX_INSTRUCTION;

    //Invalid Recipe Image
    public static final String MISSING_IMAGE_DESC = " " + PREFIX_RECIPE_IMAGE;

    //Invalid Recipe Index
    public static final String NEGATIVE_RECIPE_INDEX = "-1";
    public static final String NON_INTEGER_RECIPE_INDEX = "1.1";
    public static final String ZERO_RECIPE_INDEX = "0";

    public static final String INGREDIENT_INDEX = "1";
    public static final String RECIPE_INDEX = "1";


    /**
     * Generate the arguments of a recipe with all recipe fields except the recipe
     * field taken as argument.
     * @param field recipe field to leave out of the recipe.
     * @return
     */
    public static final String missingIngredientField(Field field) {
        switch (field) {
        case INDEX:
            return INGREDIENT_DESC_MARGARITAS;
        case INGREDIENT_NAME:
            return INGREDIENT_INDEX + " " + PREFIX_QUANTITY + VALID_QUANTITY_MARGARITAS;
        case INGREDIENT_QUANTITY:
            return INGREDIENT_INDEX + " " + PREFIX_INGREDIENT + VALID_INGREDIENT_MARGARITAS;
        default:
            System.out.println("Should not enter here");
            return null;
        }
    }

    /**
     * Generate the arguments of a recipe with all valid recipe fields except one
     * invalid field specified by the argument.
     * @param field recipe field to be invalid in the recipe.
     * @return
     */
    public static final String invalidIngredientField(Field field) {
        switch (field) {
        case INGREDIENT_NAME:
            return INGREDIENT_INDEX + " " + INVALID_INGREDIENT_DESC
                    + " " + PREFIX_QUANTITY + VALID_QUANTITY_MARGARITAS;
        case INGREDIENT_QUANTITY:
            return INGREDIENT_INDEX + " " + PREFIX_INGREDIENT + VALID_INGREDIENT_MARGARITAS
                    + INVALID_INGREDIENT_QUANTITY;
        default:
            System.out.println("Should not enter here");
            return null;
        }
    }

    /**
     * Generate the arguments of a ingredient with three types of invalid index:
     * zero index, non-integer index and negative index specified by the argument
     * @param num type of invalid index.
     * @return String.
     */
    public static final String invalidIngredientIndexField(Number num) {
        switch (num) {
        case ZERO:
            return ZERO_RECIPE_INDEX + INGREDIENT_DESC_MARGARITAS;
        case NON_INTEGER:
            return NON_INTEGER_RECIPE_INDEX + INGREDIENT_DESC_MARGARITAS;
        case NEGATIVE:
            return NEGATIVE_RECIPE_INDEX + INGREDIENT_DESC_MARGARITAS;
        default:
            System.out.println("Should not enter here");
            return null;
        }
    }

    /**
     * Generate the arguments of a recipe with all valid fields except one missing
     * field specified by the argument.
     * @param field type of field to omit
     * @return String
     */
    public static final String missingRecipeField(Field field) {
        switch (field) {
        case INGREDIENT:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + MISSING_INGREDIENT_DESC + CALORIES_DESC_MARGARITAS
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case RECIPE_NAME:
            return RECIPE_INDEX + MISSING_NAME_DESC + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case TAG:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + MISSING_TAG_DESC;
        case CALORIES:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + MISSING_CALORIES_DESC
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;

        case INSTRUCTIONS:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + MISSING_INSTRUCTIONS_DESC + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;

        case RECIPE_IMAGE:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + MISSING_IMAGE_DESC + TAG_DESC_MARGARITAS;

        case INDEX:
            return NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;

        default:
            System.out.println("Not supposed to enter here");
            return null;
        }
    }

    /**
     * Generate the arguments of a recipe with three types of invalid index:
     * zero index, non-integer index and negative index specified by the argument
     * @param num type of invalid index.
     * @return String.
     */
    public static final String invalidRecipeIndexField(Number num) {
        switch (num) {
        case ZERO:
            return ZERO_RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS
                    + CALORIES_DESC_MARGARITAS + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS
                    + TAG_DESC_MARGARITAS;
        case NON_INTEGER:
            return NON_INTEGER_RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS
                    + CALORIES_DESC_MARGARITAS
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case NEGATIVE:
            return NEGATIVE_RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS
                    + CALORIES_DESC_MARGARITAS
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        default:
            System.out.println("Should not enter here");
            return null;
        }
    }

    /**
     * Generate the arguments of a ingredient with three types of invalid calorie:
     * zero index, non-integer index and negative index specified by the argument
     * @param num type of invalid calorie.
     * @return String.
     */
    public static final String invalidRecipeCalorieField(Number num) {
        switch (num) {
        case ZERO:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + ZERO_CALORIES_DESC
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case NON_INTEGER:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + NON_INTEGER_CALORIES_DESC
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case NEGATIVE:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + NEGATIVE_CALORIES_DESC
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        default:
            System.out.println("Should not enter here");
            return null;
        }
    }

    /**
     * Generate the arguments of a recipe with all valid fields except one
     * which is specified by the argument.
     * @param field recipe field to be invalid.
     * @return String.
     */
    public static final String invalidRecipeField(Field field) {
        switch (field) {
        case INGREDIENT:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INVALID_INGREDIENT_DESC + CALORIES_DESC_MARGARITAS
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case RECIPE_NAME:
            return RECIPE_INDEX + INVALID_NAME_DESC + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + TAG_DESC_MARGARITAS;
        case TAG:
            return RECIPE_INDEX + NAME_DESC_MARGARITAS + INGREDIENT_DESC_MARGARITAS + CALORIES_DESC_MARGARITAS
                    + INSTRUCTION_DESC_MARGARITAS + RECIPE_IMAGE_DESC_MARGARITAS + INVALID_TAG_DESC;
        default:
            System.out.println("Not supposed to enter here");
            return null;
        }
    }

    static {
        DESC_MARGARITAS = new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                .withCalories(VALID_CALORIES_MARGARITAS)
                .withImage(VALID_RECIPE_IMAGE_MARGARITAS)
                .withInstruction(VALID_INSTRUCTION_MARGARITAS)
                .withTags(VALID_TAG_NOODLE).build();
        DESC_NOODLE = new EditRecipeDescriptorBuilder().withName(VALID_NAME_NOODLE)
                .withIngredient(VALID_INGREDIENT_NOODLE, VALID_QUANTITY_NOODLE)
                .withCalories(VALID_CALORIES_NOODLE)
                .withImage(VALID_RECIPE_IMAGE_NOODLE)
                .withInstruction(VALID_INSTRUCTION_NOODLE)
                .withTags(VALID_TAG_NOODLE).build();
    }

    static {
        DESC_INGREDIENT_MARGARITAS = new EditIngredientCommand.EditIngredientDescriptor();
        DESC_INGREDIENT_MARGARITAS.setIngredient(new Ingredient(VALID_INGREDIENT_MARGARITAS,
                VALID_QUANTITY_MARGARITAS));
        DESC_INGREDIENT_NOODLE = new EditIngredientCommand.EditIngredientDescriptor();
        DESC_INGREDIENT_NOODLE.setIngredient(new Ingredient(VALID_INGREDIENT_NOODLE,
                VALID_QUANTITY_NOODLE));
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
        model.updateFilteredIngredientList(new IngredientContainsKeywordsPredicate(Arrays.asList(splitName[0])));

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

    /**
     * Updates command box of command result to hold the edit ingredient command string.
     */
    public static void showEditIngredientCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                        Ingredient ingredientToEdit, Model expectedModel) {
        String editIngredientCommandString = ingredientToEdit.stringify(INDEX_FIRST_INGREDIENT.getOneBased());
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_GET_EDIT_INGREDIENT_SUCCESS,
                ingredientToEdit.toString()), CommandResult.ResultType.IS_EDIT_INGREDIENT);
        expectedCommandResult.setCommandBox(editIngredientCommandString);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Updates command box of command result to hold the edit recipe command string.
     */
    public static void showEditRecipeCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                                        Recipe recipeToEdit, Model expectedModel) {
        String editRecipeCommandString = recipeToEdit.stringify(INDEX_FIRST_INGREDIENT.getOneBased());
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_GET_EDIT_RECIPE_SUCCESS,
                recipeToEdit.toString()), CommandResult.ResultType.IS_EDIT_RECIPE);
        expectedCommandResult.setCommandBox(editRecipeCommandString);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

}
