package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecommendPredicate;

/**
 * Lists all recipes in the recipe collection with the user's ingredients to the user.
 */
public class RecommendCommand extends Command {

    public static final String COMMAND_WORD = "recommend";

    public static final String MESSAGE_SUCCESS = "Recommended recipes (according to fridge)" + "\n";

    private static Logger logger = Logger.getLogger("RecommendLogger");

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "going to start recommending");
        requireNonNull(model);
        RecommendPredicate pred = new RecommendPredicate(getIngredients(model));
        model.updateFilteredRecipeList(pred);
        ObservableList<Recipe> recipes = model.getFilteredRecipeList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            builder.append((i + 1) + ". " + recipes.get(i).getName() + "\n");
        }
        logger.log(Level.INFO, "end of recommending");
        return new CommandResult(MESSAGE_SUCCESS + builder.toString(),
                CommandResult.ResultType.IS_SHOW_RECIPE);
    }

    /**
     * Gets all the ingredients in the user's fridge
     * @param model the model to update
     * @return a list of the ingredients in the user's fridge
     */
    private List<String> getIngredients(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        ObservableList<Ingredient> ingredients = model.getFilteredIngredientList();
        ArrayList<String> keywords = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            keywords.add(ingredients.get(i).getValue());
        }
        return keywords;
    }
}
