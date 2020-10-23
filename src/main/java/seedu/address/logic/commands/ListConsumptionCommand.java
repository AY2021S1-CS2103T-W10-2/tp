package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONSUMPTION;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.consumption.Consumption;

/**
 * Lists all ingredients in the fridge to the user.
 */
public class ListConsumptionCommand extends Command {

    public static final String COMMAND_WORD = "calories";

    public static final String MESSAGE_SUCCESS = "Listed all Recipes eaten" + "\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredConsumptionList(PREDICATE_SHOW_ALL_CONSUMPTION);
        ObservableList<Consumption> consump = model.getFilteredConsumptionList();
        StringBuilder builder = new StringBuilder();
        int totalCalories = 0;
        for (int i = 0; i < consump.size(); i++) {
            builder.append((i + 1) + ". " + consump.get(i).toString() + "\n");
            totalCalories += consump.get(i).getRecipe().getCalories().value;
        }
        builder.append("Total Calories: ");
        builder.append(totalCalories + " cal");
        return new CommandResult(MESSAGE_SUCCESS + builder.toString(),
                CommandResult.ResultType.IS_SHOW_CONSUMPTION);
    }
}
