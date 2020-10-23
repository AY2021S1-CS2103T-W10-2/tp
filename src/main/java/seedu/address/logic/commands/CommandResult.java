package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    enum ResultType {
        SHOW_HELP, EXIT, IS_SHOW_RECIPE, IS_SHOW_INGREDIENT, IS_SHOW_CONSUMPTION,
        IS_EDIT_RECIPE, IS_EDIT_INGREDIENT, NONE
    }

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private boolean showHelp = false;

    /** The application should exit. */
    private boolean exit = false;

    /** Should show recipe list. */
    private boolean isShowRecipe = false;

    /** Should show ingredient list. */
    private boolean isShowIngredient = false;

    /** Should show consumption list. */
    private boolean isShowConsumption = false;

    /** Should show edit ingredient command. */
    private boolean isEditIngredient = false;

    /** Should show edit recipe command. */
    private boolean isEditRecipe = false;

    private String commandBoxText;


    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, ResultType resultType) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        switch (resultType) {
        case NONE:
            break;
        case SHOW_HELP:
            this.showHelp = true;
            break;
        case EXIT:
            this.exit = true;
            break;
        case IS_SHOW_RECIPE:
            this.isShowRecipe = true;
            break;
        case IS_SHOW_INGREDIENT:
            this.isShowIngredient = true;
            break;
        case IS_SHOW_CONSUMPTION:
            this.isShowConsumption = true;
            break;
        case IS_EDIT_RECIPE:
            this.isEditRecipe = true;
            break;
        case IS_EDIT_INGREDIENT:
            this.isEditIngredient = true;
            break;
        default:
            break;
        }
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, ResultType.NONE);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isShowRecipe() {
        return isShowRecipe;
    }

    public boolean isShowIngredient() {
        return isShowIngredient;
    }

    public boolean isShowConsumption() {
        return isShowConsumption;
    }

    public boolean isEditRecipe() {
        return isEditRecipe;
    }

    public boolean isEditIngredient() {
        return isEditIngredient;
    }

    public void setCommandBox(String str) {
        requireNonNull(str);
        commandBoxText = str;
    }

    public String getCommandBox() {
        return commandBoxText;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit, isShowRecipe, isShowIngredient, isShowConsumption,
                isEditRecipe, isEditIngredient);
    }

}
