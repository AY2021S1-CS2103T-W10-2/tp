package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;
import static seedu.address.testutil.TypicalRecipes.SANDWICH_DIFF_QTY;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.exceptions.DuplicateRecipeException;
import seedu.address.testutil.RecipeBuilder;

public class WishfulShrinkingTest {

    private final WishfulShrinking wishfulShrinking = new WishfulShrinking();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wishfulShrinking.getRecipeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wishfulShrinking.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWishfulShrinking_replacesData() {
        WishfulShrinking newData = getTypicalWishfulShrinking();
        wishfulShrinking.resetData(newData);
        assertEquals(newData, wishfulShrinking);
    }

    @Test
    public void resetData_withDuplicateRecipes_throwsDuplicateRecipeException() {
        // Two recipes with the same identity fields
        Recipe editedAlice = new RecipeBuilder(SANDWICH)
                .build();
        List<Recipe> newRecipes = Arrays.asList(SANDWICH, editedAlice);
        WishfulShrinkingStub newData = new WishfulShrinkingStub(newRecipes);

        assertThrows(DuplicateRecipeException.class, () -> wishfulShrinking.resetData(newData));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wishfulShrinking.hasRecipe(null));
    }

    @Test
    public void hasRecipe_recipeNotInWishfulShrinking_returnsFalse() {
        assertFalse(wishfulShrinking.hasRecipe(SANDWICH));
        assertFalse(wishfulShrinking.hasRecipe(SANDWICH_DIFF_QTY));
    }

    @Test
    public void hasRecipe_recipeInWishfulShrinking_returnsTrue() {
        wishfulShrinking.addRecipe(SANDWICH);
        assertTrue(wishfulShrinking.hasRecipe(SANDWICH));
        assertFalse(wishfulShrinking.hasRecipe(SANDWICH_DIFF_QTY));
    }

    @Test
    public void hasRecipe_recipeWithSameIdentityFieldsInWishfulShrinking_returnsTrue() {
        wishfulShrinking.addRecipe(SANDWICH);
        Recipe editedRecipe = new RecipeBuilder(SANDWICH)
                .build();
        Recipe editedRecipe1 = new RecipeBuilder(SANDWICH_DIFF_QTY)
                .build();
        assertTrue(wishfulShrinking.hasRecipe(editedRecipe));
        assertFalse(wishfulShrinking.hasRecipe(editedRecipe1));
    }

    @Test
    public void hasMinimalRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wishfulShrinking.hasMinimalRecipe(null));
    }

    @Test
    public void hasMinimalRecipe_recipeNotInWishfulShrinking_returnsFalse() {
        assertFalse(wishfulShrinking.hasMinimalRecipe(SANDWICH));
        assertFalse(wishfulShrinking.hasMinimalRecipe(SANDWICH_DIFF_QTY));
    }

    @Test
    public void hasMinimalRecipe_recipeInWishfulShrinking_returnsTrue() {
        wishfulShrinking.addRecipe(SANDWICH);
        assertTrue(wishfulShrinking.hasMinimalRecipe(SANDWICH));
        assertTrue(wishfulShrinking.hasMinimalRecipe(SANDWICH_DIFF_QTY));
    }

    @Test
    public void hasMinimalRecipe_recipeWithSameIdentityFieldsInWishfulShrinking_returnsTrue() {
        wishfulShrinking.addRecipe(SANDWICH);
        Recipe editedRecipe = new RecipeBuilder(SANDWICH)
                .build();
        Recipe editedRecipe1 = new RecipeBuilder(SANDWICH_DIFF_QTY)
                .build();
        assertTrue(wishfulShrinking.hasMinimalRecipe(editedRecipe));
        assertTrue(wishfulShrinking.hasMinimalRecipe(editedRecipe1));
    }


    @Test
    public void getRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wishfulShrinking.getRecipeList().remove(0));
    }

    /**
     * A stub ReadOnlyWishfulShrinking whose recipes list can violate interface constraints.
     */
    private static class WishfulShrinkingStub implements ReadOnlyWishfulShrinking {
        private final ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        private final ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
        private final ObservableList<Consumption> consumption = FXCollections.observableArrayList();

        WishfulShrinkingStub(Collection<Recipe> recipes) {
            this.recipes.setAll(recipes);
        }

        @Override
        public ObservableList<Recipe> getRecipeList() {
            return recipes;
        }

        @Override
        public ObservableList<Ingredient> getIngredientList() {
            return ingredients;
        }

        @Override
        public ObservableList<Consumption> getConsumptionList() {
            return consumption;
        }
    }

}
