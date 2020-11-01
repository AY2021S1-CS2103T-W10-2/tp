package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.INSTRUCTION_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_IMAGE_DESC_NOODLE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.NOODLE;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.recipe.AddRecipeCommand;
import seedu.address.logic.commands.recipe.DeleteRecipeCommand;
import seedu.address.logic.commands.recipe.ListRecipesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonWishfulShrinkingStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.RecipeBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonWishfulShrinkingStorage wishfulShrinkingStorage =
                new JsonWishfulShrinkingStorage(temporaryFolder.resolve("wishfulShrinking.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(wishfulShrinkingStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = DeleteRecipeCommand.COMMAND_WORD + " 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListRecipesCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListRecipesCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonWishfulShrinkingIoExceptionThrowingStub
        JsonWishfulShrinkingStorage wishfulShrinkingStorage =
                new JsonWishfulShrinkingIoExceptionThrowingStub(temporaryFolder
                .resolve("ioExceptionWishfulShrinking.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(wishfulShrinkingStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addRecipeCommand = AddRecipeCommand.COMMAND_WORD + NAME_DESC_NOODLE + INGREDIENT_DESC_NOODLE
                + INSTRUCTION_DESC_NOODLE + CALORIES_DESC_NOODLE + RECIPE_IMAGE_DESC_NOODLE;
        Recipe expectedRecipe = new RecipeBuilder(NOODLE).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addRecipe(expectedRecipe);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addRecipeCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredRecipeList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException, IOException, URISyntaxException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonWishfulShrinkingIoExceptionThrowingStub extends JsonWishfulShrinkingStorage {
        private JsonWishfulShrinkingIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveWishfulShrinking(ReadOnlyWishfulShrinking wishfulShrinking, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
