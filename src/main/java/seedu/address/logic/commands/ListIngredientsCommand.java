package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INGREDIENTS;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;

/**
 * Lists all ingredients in the fridge to the user.
 */
public class ListIngredientsCommand extends Command {

    public static final String COMMAND_WORD = "fridge";

    public static final String MESSAGE_SUCCESS = "Listed all ingredients" + "\n";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
        ObservableList<Ingredient> ingredients = model.getFilteredIngredientList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            builder.append((i + 1) + ". " + ingredients.get(i).toString() + "\n");
        }
        return new CommandResult(MESSAGE_SUCCESS + builder.toString(),
                CommandResult.ResultType.IS_SHOW_INGREDIENT);
    }
}
