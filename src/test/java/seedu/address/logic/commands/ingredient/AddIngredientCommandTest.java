package seedu.address.logic.commands.ingredient;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.IngredientBuilder;

public class AddIngredientCommandTest {

    @Test
    public void constructor_nullRecipe_throwsNullPointerException() {
        ArrayList<Ingredient> nullIngredients = new ArrayList<>();
        nullIngredients.add(null);
        assertThrows(NullPointerException.class, () -> new AddIngredientCommand(nullIngredients));
    }

    @Test
    public void execute_ingredientAcceptedByModel_addSuccessful() throws Exception {
        AddIngredientCommandTest.ModelStubAcceptingIngredientAdded modelStub = new ModelStubAcceptingIngredientAdded();
        Ingredient validIngredient = new IngredientBuilder().build();
        ArrayList<Ingredient> validIngredients = new ArrayList<>();
        validIngredients.add(validIngredient);

        CommandResult commandResult = new AddIngredientCommand(validIngredients).execute(modelStub);

        assertEquals(String.format(AddIngredientCommand.MESSAGE_SUCCESS, validIngredient.toString()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIngredient), modelStub.ingredientsAdded);
    }

    @Test
    public void execute_duplicateIngredient_throwsCommandException() {
        Ingredient validIngredient = new IngredientBuilder().build();
        ArrayList<Ingredient> validIngredients = new ArrayList<>();
        validIngredients.add(validIngredient);
        AddIngredientCommand addIngredientCommand = new AddIngredientCommand(validIngredients);
        AddIngredientCommandTest.ModelStub modelStub = new AddIngredientCommandTest
                .ModelStubWithIngredient(validIngredient);

        assertThrows(CommandException.class,
                AddIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT, () -> addIngredientCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Ingredient alice = new IngredientBuilder().withValue("Alice").build();
        Ingredient bob = new IngredientBuilder().withValue("Bob").build();
        ArrayList<Ingredient> aliceIngredients = new ArrayList<>();
        aliceIngredients.add(alice);
        ArrayList<Ingredient> bobIngredients = new ArrayList<>();
        aliceIngredients.add(bob);
        AddIngredientCommand addAliceCommand = new AddIngredientCommand(aliceIngredients);
        AddIngredientCommand addBobCommand = new AddIngredientCommand(bobIngredients);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddIngredientCommand addAliceCommandCopy = new AddIngredientCommand(aliceIngredients);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getWishfulShrinkingFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWishfulShrinkingFilePath(Path wishfulShrinkingFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWishfulShrinking(ReadOnlyWishfulShrinking newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyWishfulShrinking getWishfulShrinking() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMinimalRecipe(Recipe recipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRecipe(Recipe target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRecipe(Recipe target, Recipe editedRecipe) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Recipe> getFilteredRecipeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearRecipe() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addConsumption(Consumption target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Consumption> getFilteredConsumptionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteConsumption(Consumption target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredConsumptionList(Predicate<Consumption> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearConsumption() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMinimalIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIngredient(Ingredient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addIngredient(Ingredient ingredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIngredient(Ingredient target, Ingredient editedIngredient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Ingredient> getFilteredIngredientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearIngredient() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single ingredient.
     */
    private class ModelStubWithIngredient extends AddIngredientCommandTest.ModelStub {
        private final Ingredient ingredient;

        ModelStubWithIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            this.ingredient = ingredient;
        }

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return this.ingredient.isSameIngredient(ingredient);
        }

        @Override
        public boolean hasMinimalIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return this.ingredient.isSameIngredientName(ingredient);
        }
    }

    /**
     * A Model stub that always accept the ingredient being added.
     */
    private class ModelStubAcceptingIngredientAdded extends AddIngredientCommandTest.ModelStub {
        final ArrayList<Ingredient> ingredientsAdded = new ArrayList<>();

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return ingredientsAdded.stream().anyMatch(ingredient::isSameIngredient);
        }

        @Override
        public boolean hasMinimalIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return ingredientsAdded.stream().anyMatch(ingredient::isSameIngredientName);
        }

        @Override
        public void addIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            ingredientsAdded.add(ingredient);
        }

        @Override
        public ReadOnlyWishfulShrinking getWishfulShrinking() {
            return new WishfulShrinking();
        }
    }


}
