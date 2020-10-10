package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.commons.Calories;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;

public class ConsumptionBuilder {
    private static final Name DEFAULT_NAME = new Name("Salad");
    private static final ArrayList<Ingredient> DEFAULT_INGREDIENTS =
            new ArrayList<>(List.of(new Ingredient("Veggies"), new Ingredient("veggies")));
    private static final Calories DEFAULT_CALORIES = new Calories(10);
    private static final String DEFAULT_INSTRUCTION = "instruction";
    private static final String DEFAULT_RECIPE_IMAGE = "images/healthy1.jpg";
    private static final Set<Tag> DEFAULT_TAGS = new HashSet<>();
    public static final Recipe DEFAULT_RECIPE = new Recipe(DEFAULT_NAME, DEFAULT_INSTRUCTION, DEFAULT_RECIPE_IMAGE,
            DEFAULT_INGREDIENTS, DEFAULT_CALORIES, DEFAULT_TAGS);
    private Recipe recipe;

    /**
     * Creates a {@code IngredientBuilder} with the default details.
     */
    public ConsumptionBuilder() {
        recipe = DEFAULT_RECIPE;
    }

    /**
     * Initializes the IngredientBuilder with the data of {@code ingredientToCopy}.
     */
    public ConsumptionBuilder(Consumption consumptionToCopy) {
        recipe = consumptionToCopy.getRecipe();
    }

    /**
     * Sets the {@code value} of the {@code Ingredient} that we are building.
     */
    public ConsumptionBuilder withRecipe(Recipe recipe) {
        this.recipe = recipe;
        return this;
    }

    /**
     * Builds Ingredient
     * @return built Ingredient
     */
    public Consumption build() {
        return new Consumption(recipe);
    }
}
