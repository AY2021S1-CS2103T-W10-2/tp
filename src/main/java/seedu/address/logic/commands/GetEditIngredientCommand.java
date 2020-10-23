package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Ingredient;

public class GetEditIngredientCommand extends Command {
    public static final String COMMAND_WORD = "editF";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Permits edit of the ingredient identified by the index number used in the displayed "
            + "ingredient list" + ".\n"
            + "Parameters: " + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_GET_EDIT_INGREDIENT_SUCCESS = "Ingredient to edit shown: %1$s";

    private final Index toEdit;

    public GetEditIngredientCommand(Index toEdit) {
        this.toEdit = toEdit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (toEdit.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        Ingredient ingredientToEdit = lastShownList.get(toEdit.getZeroBased());
        int ingredientPositionToEdit = lastShownList.indexOf(ingredientToEdit) + 1;
        CommandResult commandResult =
                new CommandResult(String.format(MESSAGE_GET_EDIT_INGREDIENT_SUCCESS, ingredientToEdit.toString()),
                        CommandResult.ResultType.IS_EDIT_INGREDIENT);
        commandResult.setCommandBox(ingredientToEdit.stringify(ingredientPositionToEdit));
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetEditIngredientCommand // instanceof handles nulls
                && toEdit.equals(((GetEditIngredientCommand) other).toEdit)); // state check
    }
}
