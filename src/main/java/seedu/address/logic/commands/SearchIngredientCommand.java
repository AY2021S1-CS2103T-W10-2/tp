package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientContainsKeywordsPredicate;

/**
 * Finds and lists all ingredients in fridge whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchIngredientCommand extends Command {

    public static final String COMMAND_WORD = "searchF";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all ingredients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " banana";

    private final IngredientContainsKeywordsPredicate predicate;

    public SearchIngredientCommand(IngredientContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIngredientList(predicate);
        ObservableList<Ingredient> ingredients = model.getFilteredIngredientList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            builder.append((i + 1) + ". " + ingredients.get(i).toString().trim() + "\n");
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_INGREDIENT_LISTED_OVERVIEW, model.getFilteredIngredientList().size())
                + "\n" + builder.toString(), CommandResult.ResultType.IS_SHOW_INGREDIENT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchIngredientCommand // instanceof handles nulls
                && predicate.equals(((SearchIngredientCommand) other).predicate)); // state check
    }
}
