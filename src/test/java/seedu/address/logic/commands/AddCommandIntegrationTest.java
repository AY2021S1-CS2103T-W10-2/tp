package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.RecipeBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newRecipe_success() {
        Recipe validRecipe = new RecipeBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);

        assertCommandSuccess(new AddCommand(validRecipe), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validRecipe), expectedModel);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe recipeInList = model.getAddressBook().getRecipeList().get(0);
        assertCommandFailure(new AddCommand(recipeInList), model, AddCommand.MESSAGE_DUPLICATE_Recipe);
    }

}
