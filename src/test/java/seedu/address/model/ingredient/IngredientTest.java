package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null));
    }

    @Test
    public void isValidIngredient() {
        // null name
        assertThrows(NullPointerException.class, () -> Ingredient.isValidIngredient(null));

        // invalid ingredient names
        assertFalse(Ingredient.isValidIngredient(new Ingredient("", "2 cups"))); // empty name
        assertFalse(Ingredient.isValidIngredient(new Ingredient("fishc@ke", "2 cups"))); // empty name
        assertFalse(Ingredient.isValidIngredient(new Ingredient("fishcake!", "2 cups"))); // empty name

        // valid ingredient names
        assertTrue(Ingredient.isValidIngredient(new Ingredient("white cabbage", "2 cups"))); // space within name
        assertTrue(Ingredient.isValidIngredient(new Ingredient("pea", "2 cups")));
        assertTrue(Ingredient.isValidIngredient(new Ingredient("93121534", "2 cups")));
        assertTrue(Ingredient.isValidIngredient(new Ingredient("loooooooooooongbean", "2 cups"))); // long

        // valid ingredient quantities
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage", "2 cups"))); // space within quantity
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage", " 2 cups "))); // space before and after
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage", "2cups"))); // no space within quantity
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage", "2/3 cups"))); // fraction quantity
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage", "2.3 cups"))); // decimal
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage", "pinch of"))); // no numbers in quantity
        assertTrue(Ingredient.isValidIngredient(new Ingredient("cabbage"))); // no quantity

        // invalid ingredient quantities
        assertFalse(Ingredient.isValidIngredient(new Ingredient("fishcake", "2 c@ps"))); // empty name
        assertFalse(Ingredient.isValidIngredient(new Ingredient("fishcake", "2 cups!"))); // empty name
        assertFalse(Ingredient.isValidIngredient(new Ingredient("fishcake",
                "0.0000000000000000000000000000000000000000000005 cups"))); // empty name

    }
}
