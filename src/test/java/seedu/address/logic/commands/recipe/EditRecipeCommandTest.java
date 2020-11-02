package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;
import seedu.address.testutil.RecipeBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditRecipeCommandTest {

    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Recipe editedRecipe = new RecipeBuilder().build();
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(editedRecipe).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRecipe = Index.fromOneBased(model.getFilteredRecipeList().size());
        Recipe lastRecipe = model.getFilteredRecipeList().get(indexLastRecipe.getZeroBased());

        RecipeBuilder recipeInList = new RecipeBuilder(lastRecipe);
        Recipe editedRecipe = recipeInList.withName(VALID_NAME_MARGARITAS)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
              .build();

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(indexLastRecipe, descriptor);

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(lastRecipe, editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, new EditRecipeDescriptor());
        Recipe editedRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_NOT_EDITED, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeInFilteredList = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        Recipe editedRecipe = new RecipeBuilder(recipeInFilteredList).withName(VALID_NAME_MARGARITAS).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE,
                new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS).build());

        String expectedMessage = String.format(EditRecipeCommand.MESSAGE_EDIT_RECIPE_SUCCESS, editedRecipe);

        Model expectedModel = new ModelManager(new WishfulShrinking(model.getWishfulShrinking()), new UserPrefs());
        expectedModel.setRecipe(model.getFilteredRecipeList().get(0), editedRecipe);

        assertCommandSuccess(editRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRecipeUnfilteredList_failure() {
        Recipe firstRecipe = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder(firstRecipe).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_SECOND_RECIPE, descriptor);

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_duplicateRecipeFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        // edit recipe in filtered list into a duplicate in recipe collection
        Recipe recipeInList = model.getWishfulShrinking().getRecipeList().get(INDEX_SECOND_RECIPE.getZeroBased());
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE,
                new EditRecipeDescriptorBuilder(recipeInList).build());

        assertCommandFailure(editRecipeCommand, model, EditRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_invalidRecipeIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS).build();
        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of recipe collection
     */
    @Test
    public void execute_invalidRecipeIndexFilteredList_failure() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);
        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of recipe collection list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getRecipeList().size());

        EditRecipeCommand editRecipeCommand = new EditRecipeCommand(outOfBoundIndex,
                new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS).build());

        assertCommandFailure(editRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditRecipeCommand standardCommand = new EditRecipeCommand(INDEX_FIRST_RECIPE, DESC_NOODLE);

        // same values -> returns true
        EditRecipeDescriptor copyDescriptor = new EditRecipeDescriptor(DESC_NOODLE);
        EditRecipeCommand commandWithSameValues = new EditRecipeCommand(INDEX_FIRST_RECIPE, copyDescriptor);

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearRecipeCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditRecipeCommand(INDEX_SECOND_RECIPE, DESC_NOODLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditRecipeCommand(INDEX_FIRST_RECIPE, DESC_MARGARITAS)));
    }

}
